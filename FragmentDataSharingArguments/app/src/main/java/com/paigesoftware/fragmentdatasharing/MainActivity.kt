package com.paigesoftware.fragmentdatasharing

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), VideoIntentFragment.OnFragmentVideoUri {

    companion object {
        val TAG = MainActivity::class.qualifiedName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoIntentFragment = VideoIntentFragment.newInstance()
        replaceFragment(videoIntentFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_constraintlayout, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri?) {
        Log.d(TAG, "video uri: $uri")
        uri?.let {
            val videoViewFragment = VideoViewFragment.newInstance(uri)
            replaceFragment(videoViewFragment)
        }
    }

}