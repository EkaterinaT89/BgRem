package com.bgrem.presentation.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bgrem.R
import com.bgrem.databinding.FragmentAddNewVideoBinding
import com.bgrem.domain.utils.MediaUtils
import com.bgrem.domain.utils.PermissionsManager
import com.bgrem.presentation.viewmodel.VideoViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.material.snackbar.Snackbar
import java.io.File

class AddNewVideoFragment: Fragment() {

    private var mediaPlayer: ExoPlayer? = null

    private var videoBinding: FragmentAddNewVideoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentAddNewVideoBinding.inflate(
            inflater,
            container,
            false
        )

        videoBinding = binding

        val viewModel: VideoViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val permissionsRequestCode = 963

        lateinit var permissionManager: PermissionsManager

        val permissions = listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )

        permissionManager =
            PermissionsManager(requireActivity(), permissions, permissionsRequestCode)

        val pickVideoResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val data = it.data
                when (it.resultCode) {
                    Activity.RESULT_OK -> {
                        val videoUri = data?.data!!
                        val videoPath = MediaUtils.getRealPathFromUri(videoUri, requireActivity())

                        if (videoPath != null) {
                            viewModel.getJob(
                                File(videoPath),
                                1
                            )
                        }
                    }
                }
            }

        with(binding) {
            pickVideo.setOnClickListener {
                videoAdded.visibility = View.VISIBLE

                if (!permissionManager.checkPermissions()) {
                    permissionManager.requestPermissions()
                    Snackbar.make(
                        binding.root,
                        getString(R.string.grant_storage_permissions_dialog_message),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(getString(R.string.everything_fine)) {}
                        .show()
                    return@setOnClickListener
                }

                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                )
                pickVideoResult.launch(intent)

                viewModel.createJob()
                viewModel.getBg()

            }

            videoNameInput.requestFocus()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (com.google.android.exoplayer2.util.Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (com.google.android.exoplayer2.util.Util.SDK_INT < 24 || mediaPlayer == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (com.google.android.exoplayer2.util.Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (com.google.android.exoplayer2.util.Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun initializePlayer() {
        mediaPlayer = ExoPlayer.Builder(requireContext())
            .build()
            .also {
                videoBinding?.videoPlayerView?.player = it
            }
    }

    private fun releasePlayer() {
        mediaPlayer?.run {
            release()
        }
        mediaPlayer = null
    }

}