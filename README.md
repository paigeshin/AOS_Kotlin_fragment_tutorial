### Fragment Lifecycle

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9375b8ce-396e-4a1d-878e-ab14b89f0e5a/fragmentlifecycle.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9375b8ce-396e-4a1d-878e-ab14b89f0e5a/fragmentlifecycle.png)

- `onCreate()` 에서 view를 접근할 수 있다.
- `onCreateView()` 에서 view를 접근할 수 있다. `Fragment` 에 argument로 `layout` 을 제공하면 onCreateView를 호출하지 않아도 된다.
- `onViewCreated()` 에서 view를 접근할 수 있다.

# NavigationDrawer & ViewPager & BottomNavigationView

### MainActivity

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout
    android:id="@+id/drawerlayout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <androidx.constraintlayout.widget.ConstraintLayout

        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".basicfragment.BasicFragmentActivity">

        <FrameLayout
            android:id="@+id/fragment_container_basicfragmentactivity"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintBottom_toTopOf="@+id/bottomnavtionview"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/toolbar" />

        <com.google.android.material.appbar.MaterialToolbar
            android:id="@+id/toolbar"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:background="?attr/colorPrimary"
            android:minHeight="?attr/actionBarSize"
            android:theme="?attr/actionBarTheme"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <com.google.android.material.bottomnavigation.BottomNavigationView
            android:id="@+id/bottomnavtionview"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:menu="@menu/menu_fragments" />

    </androidx.constraintlayout.widget.ConstraintLayout>

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/navigationview_basicfragmentactivity"
        app:menu="@menu/menu_fragments"
        android:layout_gravity="start"
        android:layout_width="wrap_content"
        android:layout_height="match_parent">

    </com.google.android.material.navigation.NavigationView>
    
</androidx.drawerlayout.widget.DrawerLayout>
```

### Fragment 1 & Fragment 2

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".basicfragment.BasicFirstImageFragment">

    <!-- TODO: Update blank fragment layout -->
    <ImageView
        android:id="@+id/imageview_basicfirstimagefragment"
        android:scaleType="centerCrop"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <ProgressBar
        android:id="@+id/progressbar_basicfirstimagefragment"
        android:visibility="invisible"
        android:layout_gravity="center_vertical|center_horizontal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</FrameLayout>

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".basicfragment.BasicFirstImageFragment">

    <!-- TODO: Update blank fragment layout -->
    <ImageView
        android:id="@+id/imageview_basicsecondimagefragment"
        android:scaleType="centerCrop"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <ProgressBar
        android:id="@+id/progressbar_basicsecondimagefragment"
        android:visibility="invisible"
        android:layout_gravity="center_vertical|center_horizontal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</FrameLayout>
```

### menu.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/first_fragment_menu"
        android:title="go to first fragment"
        android:icon="@android:drawable/ic_menu_report_image"/>
    <item
        android:id="@+id/second_fragment_menu"
        android:title="go to second fragment_menu"
        android:icon="@android:drawable/ic_dialog_email"/>
</menu>
```

### MainActivity

```kotlin
package com.paigesoftware.fragmenttutorial.basicfragment

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.paigesoftware.fragmenttutorial.R
import kotlinx.android.synthetic.main.activity_basic_fragment.*
import java.lang.ClassCastException

class BasicFragmentActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    //set drawer event with navigation drawer
    private val drawerToggle by lazy {
        ActionBarDrawerToggle(
            this, drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_fragment)

        setSupportActionBar(toolbar)

        //navigation drawer, navigation view
        navigationview_basicfragmentactivity.setNavigationItemSelectedListener {
            selectItem(it)
            //selectDrawerItem(it)
            true
        }

        //custom toolbar
        drawerlayout.addDrawerListener(drawerToggle)

        //pager adapter
        val pagerAdapter = ImageFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        //bottom navigation view
        bottomnavtionview.setOnNavigationItemSelectedListener {
            selectItem(it)
            true
        }

        //viewpager & bottom navigationview sync
        viewPager.addOnPageChangeListener(this)

    }

    // Bottom Navigation View
    private fun selectItem(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first_fragment_menu -> {
                viewPager.currentItem = 0

            }
            R.id.second_fragment_menu -> {
                viewPager.currentItem = 1

            }
            else -> viewPager.currentItem = 0
        }
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun selectDrawerItem(item: MenuItem) {
        /* set navigation drwaer item with ViewPager */
        when (item.itemId) {
            R.id.first_fragment_menu -> viewPager.currentItem = 0
            R.id.second_fragment_menu -> viewPager.currentItem = 1
            else -> viewPager.currentItem = 0
        }
        drawerlayout.closeDrawer(GravityCompat.START)

        /* Only Drawer LAYOUT */
//        var fragment: Fragment? = null
//        val fragmentClass = when (item.itemId) {
//            R.id.first_fragment_menu -> BasicFirstImageFragment::class.java
//            R.id.second_fragment_menu -> BasicSecondImageFragment::class.java
//            else -> BasicFirstImageFragment::class.java
//        }
//        try {
//            fragment = fragmentClass.newInstance() as Fragment
//        } catch (e: ClassCastException) {
//            e.printStackTrace()
//        }
//        replaceFragment(fragment)
        drawerlayout.closeDrawer(GravityCompat.START)
    }

    //menu drop down
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(
            item
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fragments, menu)
        return true
    }

    private fun replaceFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_basicfragmentactivity, fragment)
                .commit()
        }

    }

    //viewpager와 bottom navigation view를 sync
    //drawer 도 sync된다.
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val currentMenuItem = bottomnavtionview.menu[position].itemId
        if(currentMenuItem != bottomnavtionview.selectedItemId) {
            bottomnavtionview.menu.getItem(position).isChecked = true
            bottomnavtionview.menu.findItem(bottomnavtionview.selectedItemId).isChecked = false
        }
    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

}
```

### Fragment

```kotlin
package com.paigesoftware.fragmenttutorial.basicfragment

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.paigesoftware.fragmenttutorial.R
import kotlinx.android.synthetic.main.fragment_basic_first_image.*

class BasicFirstImageFragment: Fragment() {

    private val imageURL = "https://www.bestbrides.net/wp-content/uploads/2020/05/Shanghai-women.jpg"

    companion object {
        fun newInstance() = BasicFirstImageFragment()
    }

    private fun loadImageUsingGlide() {
        progressbar_basicfirstimagefragment.visibility = View.VISIBLE
        Glide.with(this)
            .asBitmap()
            .load(imageURL)
            .into(object : BitmapImageViewTarget(imageview_basicfirstimagefragment){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                    progressbar_basicfirstimagefragment.visibility = View.GONE
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImageUsingGlide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_first_image, container, false)
    }

}
```

### ViewPager

```kotlin
package com.paigesoftware.fragmenttutorial.basicfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ImageFragmentPagerAdapter(fragmentManger: FragmentManager): FragmentPagerAdapter(fragmentManger, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> BasicFirstImageFragment.newInstance()
            1 -> BasicSecondImageFragment.newInstance()
            else -> BasicFirstImageFragment.newInstance()
        }
    }

}
```

# Data Sharing between Fragments

### Between Activity & Fragment

- onAttach
- interface

### Receive Data From Fragment

- MainActivity

```kotlin
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
        Toast.makeText(this, "uri: ${uri.toString()}", Toast.LENGTH_LONG).show()
    }

}
```

- VideoIntentFragment

```kotlin
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
```

### Send data to Fragment From Activity

- VideoViewFragment

```kotlin
//receiving data.
//bundle & argument 로 데이터를 주고 받음
//기본적으로 key-value로 이루어져있다.
class VideoViewFragment : Fragment() {

    private var videoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // argument에 데이터가 있을 시에 가져온다.
        arguments?.let {
            videoUri = it.getParcelable<Uri>(VIDEO_URI)
        }
    }

    override fun onStart() {
        super.onStart()
        videoview.setVideoURI(videoUri)
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

        //instantiated 될 때, arguments를 붙여준다.
        fun newInstance(uri: Uri): VideoViewFragment {
            val fragment = VideoViewFragment()
            val args = Bundle()
            args.putParcelable(VIDEO_URI, uri)
            fragment.arguments = args
            return fragment
        }

    }
}
```

# Data Sharing Using ViewModel

- You don't need interface to share data.

- App Level Gradle

```kotlin
implementation "android.arch.lifecycle:extensions:1.1.1"
```

- VideoUriViewModel

```kotlin
//viewModel은 activity가 죽을 때 사라짐.
class VideoUriViewModel: ViewModel() {

    var videoUri: Uri? = null

}
```

- VideoIntentFragment

```kotlin
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
```

- ViedoViewFragment

```kotlin
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
```

- MainActivity

```kotlin
class MainActivity : AppCompatActivity() {

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

/*** fragment data sharing using interface & arguments ***/
//    override fun onFragmentInteraction(uri: Uri?) {
//        Log.d(TAG, "video uri: $uri")
//        uri?.let {
//            val videoViewFragment = VideoViewFragment.newInstance(uri)
//            replaceFragment(videoViewFragment)
//        }
//    }

}
```