package com.intern.assignment.ui.activity.home


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.intern.assignment.R

import com.intern.assignment.databinding.ActivityHomeBinding
import com.intern.assignment.repository.HomeRepository
import com.intern.assignment.ui.adapter.NewsCategoryAdapter


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var viewPager: ViewPager
    private lateinit var mTabLayout: TabLayout

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        val homeRepository = HomeRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(homeRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        initViews()

    }

    private fun initViews() {

        // initialise the layout
        viewPager = binding.viewpager
        mTabLayout = binding.tabLayout

        // setOffscreenPageLimit means number
        // of tabs to be shown in one page
        viewPager.offscreenPageLimit = 4
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(mTabLayout))

        mTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // setCurrentItem as the tab position
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        setFragmentToTabs()
    }

    // show all the tab using NewsCategoryAdapter
    private fun setFragmentToTabs() {

        val list = ArrayList<String>()
        list.add("Business")
        list.add("Entertainment")
        list.add("General")
        list.add("Health")
        list.add("Science")
        list.add("Sports")
        list.add("Technology")

        for (cate in list) {
            mTabLayout.addTab(mTabLayout.newTab().setText(cate))
        }
        val newsCategoryAdapter = NewsCategoryAdapter(
            supportFragmentManager,
            mTabLayout.tabCount,
            list
        )

        viewPager.apply {
            adapter = newsCategoryAdapter
            currentItem = 0
        }
    }
}


