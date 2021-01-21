package com.paigesoftware.fragmenttutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.paigesoftware.fragmenttutorial.basicfragment.BasicFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_go_to_basic_fragment.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button_go_to_basic_fragment -> {
                startActivity(Intent(this, BasicFragmentActivity::class.java))
            }
        }
    }

}