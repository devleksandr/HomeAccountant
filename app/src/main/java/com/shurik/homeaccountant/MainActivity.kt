package com.shurik.homeaccountant

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.R.attr.fragment
import android.util.Log
import com.shurik.homeaccountant.utils.replaceFragments
import kotlinx.android.synthetic.main.fragment_start.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var savedInstanceState : Bundle

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)

        onSetHomeFragment()

        replaceFragments(
                fragment = StartFragment(),
                allowStateLoss = true,
                containerViewId = R.id.content_frame
        )

        }

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

        R.id.navigation_accruals -> {
            replaceFragments(
                    fragment = AccrualsListFragment(),
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

        val fragment = supportFragmentManager.fragments
        val string = fragment.toString()
        val index1 = string.indexOf('[') +1
        val index2 = string.indexOf('{')
        val mFragmentTag = if (index1 == -1 && index2 == -1) null else string.substring(index1,index2)
        Log.d("FRAGMENTS",mFragmentTag)
        if (mFragmentTag=="StartFragment") {
                if (web_view.canGoBack()) {
                    web_view.goBack()
                }
            else {
                    super.onBackPressed()
                }
        }
        else {
            super.onBackPressed()
            }
        }

    private fun onSetHomeFragment() {
        val fragment: Fragment = StartFragment()
        val mTransaction = supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment,fragment.javaClass.simpleName)
        mTransaction.commit()
        bottomNavigationView.selectedItemId
    }
}