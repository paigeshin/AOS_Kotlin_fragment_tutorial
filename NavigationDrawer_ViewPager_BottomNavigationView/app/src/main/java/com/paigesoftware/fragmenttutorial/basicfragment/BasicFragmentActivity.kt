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