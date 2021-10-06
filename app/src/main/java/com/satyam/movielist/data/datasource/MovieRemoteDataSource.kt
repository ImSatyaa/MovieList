package com.satyam.movielist.data.datasource

import com.satyam.movielist.network.MovieAPIService
import javax.inject.Inject
import androidx.paging.PagingSource
import com.satyam.movielist.model.Search


class MovieRemoteDataSource @Inject constructor(private val movieAPIService: MovieAPIService) :
    PagingSource<Int, Search>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = movieAPIService.getMovies("fast",currentLoadingPageKey)
            val responseData = mutableListOf<Search>()
            val data = response.Search
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}