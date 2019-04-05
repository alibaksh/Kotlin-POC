package com.flutterpakistan.app.gettingstarted

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created on 4/3/19.
 */

class GettingStartedFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> return SplashFragment()
            else -> return LoginFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}
