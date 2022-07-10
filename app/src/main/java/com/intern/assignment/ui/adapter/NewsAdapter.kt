package com.intern.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intern.assignment.R
import com.intern.assignment.data.models.Article
import com.intern.assignment.data.models.NewsResponse
import com.intern.assignment.databinding.NewsLayoutBinding
import com.intern.assignment.ui.adapter.NewsAdapter.*

class NewsAdapter: PagingDataAdapter<Article, NewsViewHolder>(NewsDiffUtil) {

    inner class NewsViewHolder(val binding : NewsLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.binding.apply {
            Glide.with(root.context).load(article?.urlToImage)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .into(imgArticle)
            txtTitle.text = article?.title

            root.setOnClickListener {
                onItemClickListener?.let {
                    if (article != null) {
                        it(article)
                    }
                }
            }
        }

    }

    object NewsDiffUtil: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            // Id is unique.
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }
}