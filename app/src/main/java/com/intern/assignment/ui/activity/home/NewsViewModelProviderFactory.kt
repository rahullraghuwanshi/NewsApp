package com.intern.assignment.ui.activity.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intern.assignment.repository.HomeRepository

class NewsViewModelProviderFactory(
    private val homeRepository: HomeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(homeRepository) as T
    }
}