package com.flutterpakistan.app.gettingstarted

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.flutterpakistan.app.R

/**
 * Created on 4/1/19.
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initUI()
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }

    fun initUI() {

        viewPager = findViewById(R.id.pager)
        val fragmentAdapter = GettingStartedFragmentAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
    }
}