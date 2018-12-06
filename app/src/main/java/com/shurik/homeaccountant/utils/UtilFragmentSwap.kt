package com.shurik.homeaccountant.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.shurik.homeaccountant.R

fun AppCompatActivity.replaceFragments(fragment: Fragment,
                                       allowStateLoss: Boolean = false,
                                       @IdRes containerViewId: Int) {
    val ft = supportFragmentManager
            .beginTransaction()
            .replace(containerViewId,fragment,fragment.javaClass.simpleName)
            .setCustomAnimations(R.anim.left, R.anim.right)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    }
    else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}