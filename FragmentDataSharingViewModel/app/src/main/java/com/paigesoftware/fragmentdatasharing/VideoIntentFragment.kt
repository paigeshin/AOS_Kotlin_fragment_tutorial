package com.paigesoftware.fragmentdatasharing

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_video_intent.*

class VideoIntentFragment : Fragment() {

    private var videoUri: Uri? = null
    // Using the ViewModel to pass the videoUri
    private val videoUriViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(VideoUriViewModel::class.java) }
    }
/*** fragment data sharing using interface & arguments ***/
//    private var videoUriListener: OnFragmentVideoUri? = null

/*** fragment data sharing using interface & arguments ***/
//    interface OnFragmentVideoUri {
//        fun onFragmentInteraction(uri: Uri?)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_intent, container, false)
    }

    //ViewModel
    private fun startVideoViewFragment() {
        val videoViewFragment = VideoViewFragment.newInstance()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container_constraintlayout, videoViewFragment)?.addToBackStack(null)?.commit()
    }

    private fun callVideoApp() {
        val videoCaptureIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(videoCaptureIntent, VIDEO_APP_REQUEST_CODE)
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
/*** fragment data sharing using interface & arguments ***/
//            videoUriListener?.onFragmentInteraction(videoUri)
            // Using the ViewModel to pass the videoUri
            videoUriViewModel?.videoUri = videoUri
            startVideoViewFragment()
        }

    }

/*** fragment data sharing using interface & arguments ***/
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if(context is OnFragmentVideoUri) {
//            videoUriListener = context
//        } else {
//            throw RuntimeException("$context must implement OnFragmentInteractionListener")
//        }
//    }

/*** fragment data sharing using interface & arguments ***/
//    override fun onDetach() {
//        super.onDetach()
//        videoUriListener = null
//    }

    companion object {
        val TAG = VideoIntentFragment::class.qualifiedName
        fun newInstance() = VideoIntentFragment()
        const val VIDEO_APP_REQUEST_CODE = 1002
    }
}