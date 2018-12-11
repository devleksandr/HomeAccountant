package com.shurik.homeaccountant

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.shurik.homeaccountant.utils.replaceFragments
import kotlinx.android.synthetic.main.fragment_start.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getting reference of bottom nav and applying listener on it
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)

        //making start fragment visible
        replaceFragments(
                fragment = StartFragment(),
                allowStateLoss = true,
                containerViewId = R.id.content_frame
        )

        }

//bottom navigation listener
private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { p0 ->
    when (p0.itemId) {
        R.id.navigation_start -> {
            replaceFragments(
                    fragment = StartFragment(),
                    allowStateLoss = true,
                    containerViewId = R.id.content_frame
            )
            return@OnNavigationItemSelectedListener true
        }

        R.id.navigation_add -> {
            replaceFragments(
                    fragment = AddCountersFragment(),
                    allowStateLoss = true,
                    containerViewId = R.id.content_frame
            )
            return@OnNavigationItemSelectedListener true
        }

        R.id.navigation_counts -> {
            replaceFragments(
                    fragment = CountListFragment(),
                    allowStateLoss = true,
                    containerViewId = R.id.content_frame
            )
            return@OnNavigationItemSelectedListener true
        }

        R.id.navigation_taxes -> {
            replaceFragments(
                    fragment = TaxesFragment(),
                    allowStateLoss = true,
                    containerViewId = R.id.content_frame
            )
            return@OnNavigationItemSelectedListener true
        }
    }
    false
}

    override fun onBackPressed() {
        //getting current fragment name and converting it to string
        val fragment = supportFragmentManager.fragments
        val string = fragment.toString()
        val index1 = string.indexOf('[') +1
        val index2 = string.indexOf('{')
        val mFragmentTag = if (index1 == -1 && index2 == -1) null else string.substring(index1,index2)
        //if we are on start fragment and using web view, we navigating on there
        if (mFragmentTag=="StartFragment") {
                if (web_view.canGoBack()) {
                    web_view.goBack()
                }
            //if not - we leave an app
            else {
                    super.onBackPressed()
                }
        }
        else {
            super.onBackPressed()
            }
        }
}