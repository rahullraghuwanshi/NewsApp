package com.intern.assignment.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.intern.assignment.data.models.Article
import com.intern.assignment.ui.fragment.newsfragment.NewsPagingSource
import com.intern.assignment.util.Constants.Companion.QUERY_PAGE_SIZE

class HomeRepository {

    fun getCategoryNews(category: String): LiveData<PagingData<Article>> {

        return Pager(
            config = PagingConfig(
                pageSize = QUERY_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                NewsPagingSource(category)
            }, initialKey = 1
        ).liveData
    }

}