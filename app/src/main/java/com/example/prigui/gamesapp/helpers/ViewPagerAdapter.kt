package com.example.prigui.gamesapp.helpers

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import java.util.ArrayList

/**
 * Created by prigui on 27/08/2018.
 */
class ViewPagerAdapter(manager: android.support.v4.app.FragmentManager) : FragmentStatePagerAdapter(manager) {

    var mFragmentList: List<Fragment> = ArrayList()
    private var mFragmentTitleList: List<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }


    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList += fragment
        mFragmentTitleList += title
    }

    fun setFrags(fragments: ArrayList<Fragment>, titles: List<String>? = null) {
        mFragmentList = fragments
        titles?.let { mFragmentTitleList = it }
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
}