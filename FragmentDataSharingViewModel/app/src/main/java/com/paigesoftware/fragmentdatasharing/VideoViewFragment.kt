package com.paigesoftware.fragmentdatasharing

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_video_view.*
import java.net.URI

//receiving data.
//bundle & argument 로 데이터를 주고 받음
//기본적으로 key-value로 이루어져있다.
class VideoViewFragment : Fragment() {

    /*** fragment data sharing using interface & arguments ***/
//    private var videoUri: Uri? = null

    // Using the ViewModel to pass the videoUri
    private val videoUriViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(VideoUriViewModel::class.java) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*** fragment data sharing using interface & arguments ***/
        // argument에 데이터가 있을 시에 가져온다.
//        arguments?.let {
//            videoUri = it.getParcelable<Uri>(VIDEO_URI)
//        }

    }

    override fun onStart() {
        super.onStart()
        // Using the ViewModel to pass the videoUri
        videoUriViewModel?.let { videoview.setVideoURI(it.videoUri) }
        videoview.start()
    }

    override fun onPause() {
        super.onPause()
        videoview.pause()
    }

    override fun onStop() {
        videoview.stopPlayback()
        super.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_view, container, false)
    }

    companion object {

        private val TAG = VideoIntentFragment::class.qualifiedName

        const val VIDEO_URI = "video_uri"

/*** fragment data sharing using interface & arguments ***/
        //instantiated 될 때, arguments를 붙여준다.
//        fun newInstance(uri: Uri): VideoViewFragment {
//            val fragment = VideoViewFragment()
//            val args = Bundle()
//            args.putParcelable(VIDEO_URI, uri)
//            fragment.arguments = args
//            return fragment
//        }

        @JvmStatic fun newInstance() = VideoViewFragment()

    }
}