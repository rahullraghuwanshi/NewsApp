package com.intern.assignment.ui.activity.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.intern.assignment.data.models.Article
import com.intern.assignment.repository.HomeRepository

class NewsViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {


    fun getCategoryNews(category: String): LiveData<PagingData<Article>> {
        return homeRepository.getCategoryNews(category).cachedIn(viewModelScope)
    }
}