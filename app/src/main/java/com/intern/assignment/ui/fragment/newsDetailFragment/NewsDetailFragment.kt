package com.intern.assignment.ui.fragment.newsDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.intern.assignment.R
import com.intern.assignment.data.models.Article
import com.intern.assignment.databinding.FragmentNewsDetailBinding
import com.intern.assignment.ui.fragment.WebViewFragment

class NewsDetailFragment : Fragment() {

    lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        setUpToolbar()

        val article = arguments?.getSerializable("article") as Article
        setData(article)

        binding.readMore.setOnClickListener {
            setFragment(article)
        }

        return binding.root
    }

    private fun setData(article: Article) {
        binding.apply {
            txtTitle.text = article.title
            txtDescription.text = article.description
            Glide.with(root.context).load(article.urlToImage)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .into(imgArticle)
        }

    }

    private fun setUpToolbar() {
      binding.toolbar.apply {
          setNavigationIcon(R.drawable.ic_back_button);
          setNavigationOnClickListener {
              activity?.onBackPressed()
          }
      }
    }

    private fun setFragment(article : Article) {
        val mFragment = WebViewFragment()
        val bundle = Bundle()
        bundle.putSerializable("article", article)
        mFragment.arguments = bundle

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .addToBackStack("WebViewFragment")
            .replace(R.id.fram_container, mFragment).commit()
    }
}