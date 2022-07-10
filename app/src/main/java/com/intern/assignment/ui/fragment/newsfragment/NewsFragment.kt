package com.intern.assignment.ui.fragment.newsfragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.assignment.databinding.FragmentNewsBinding
import com.intern.assignment.ui.activity.detail.DetailActivity
import com.intern.assignment.ui.activity.home.HomeActivity
import com.intern.assignment.ui.activity.home.NewsViewModel
import com.intern.assignment.ui.adapter.NewsAdapter
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        viewModel = (activity as HomeActivity).viewModel


        val adapter = NewsAdapter()
        adapter.setOnItemClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("article",it)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(context)

        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = layoutManager

        lifecycleScope.launch {
            viewModel.getCategoryNews(arguments?.getString("category").toString().lowercase())
                .observe(viewLifecycleOwner, Observer {
                    it?.let {
                        adapter.submitData(lifecycle, it)
                    }
                })
        }


        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding.progressBar.visibility = View.VISIBLE
            else {
                binding.progressBar.visibility = View.GONE
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }

        return binding.root
    }

}