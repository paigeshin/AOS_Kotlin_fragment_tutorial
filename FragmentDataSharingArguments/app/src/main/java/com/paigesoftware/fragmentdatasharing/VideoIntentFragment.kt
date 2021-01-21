package com.paigesoftware.fragmentdatasharing

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_video_intent.*
import java.lang.RuntimeException

class VideoIntentFragment : Fragment() {

    private var videoUri: Uri? = null
    private var videoUriListener: OnFragmentVideoUri? = null

    interface OnFragmentVideoUri {
        fun onFragmentInteraction(uri: Uri?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_intent, container, false)
    }

    private fun callVideoApp() {

        val videoCaptureIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(videoCaptureIntent, VIDEO_APP_REQUEST_CODE)
        /*
        Activities are registered in the manifest. The OS basically has a database of all registered activities and their <intent-filter> details, and it uses that database to find candidates for any given implicit Intent.
        * */
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            VIDEO_APP_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK) {
                    videoUri = data?.data
                }
            }
            else -> Log.e(TAG, "Unrecognized request code $requestCode")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_record.setOnClickListener {
            callVideoApp()
        }

        button_play.setOnClickListener {
            videoUriListener?.onFragmentInteraction(videoUri)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnFragmentVideoUri) {
            videoUriListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        videoUriListener = null
    }

    companion object {
        val TAG = VideoIntentFragment::class.qualifiedName
        fun newInstance() = VideoIntentFragment()
        const val VIDEO_APP_REQUEST_CODE = 1002
    }
}