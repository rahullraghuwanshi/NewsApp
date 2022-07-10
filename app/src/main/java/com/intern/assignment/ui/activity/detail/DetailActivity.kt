package com.intern.assignment.ui.activity.detail


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.intern.assignment.R
import com.intern.assignment.data.models.Article
import com.intern.assignment.databinding.ActivityDetailBinding
import com.intern.assignment.ui.fragment.newsDetailFragment.NewsDetailFragment


class DetailActivity : AppCompatActivity() {

    private lateinit var binding  : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val article : Article = intent.getSerializableExtra("article") as Article

        setFragment(article)

    }

    private fun setFragment(article : Article) {
        val mFragment = NewsDetailFragment()
        val bundle = Bundle()
        bundle.putSerializable("article", article)
        mFragment.arguments = bundle

        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()

            .replace(R.id.fram_container, mFragment).commit()
    }

    override fun onBackPressed() {
        val fm = fragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}