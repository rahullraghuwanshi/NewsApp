package com.intern.assignment.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.intern.assignment.ui.fragment.newsfragment.NewsFragment
import kotlin.collections.ArrayList


class NewsCategoryAdapter(
    private val fm: FragmentManager,
    private val mNumOfTabs: Int,
    private val list: ArrayList<String>
): FragmentStatePagerAdapter(fm) {

    // get the current item with position number
    override fun getItem(position: Int): Fragment {
        val b = Bundle()
        b.putString("category", list[position])
        val frag: Fragment = NewsFragment()
        frag.arguments = b
        return frag
    }

    // get total number of tabs
    override fun getCount(): Int {
        return mNumOfTabs
    }
}