package com.intern.assignment.ui.fragment.newsfragment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.intern.assignment.data.api.RetrofitInstance
import com.intern.assignment.data.models.Article

class NewsPagingSource(private val category: String) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = RetrofitInstance.api.getCategoryNews(category = category)
            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}