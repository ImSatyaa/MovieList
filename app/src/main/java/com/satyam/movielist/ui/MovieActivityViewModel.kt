package com.satyam.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.satyam.movielist.data.datasource.MovieRemoteDataSource
import com.satyam.movielist.network.MovieAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieActivityViewModel @Inject constructor(
    private val movieAPIService: MovieAPIService
) : ViewModel() {

    val movieList = Pager(PagingConfig(pageSize = 2)) {
        MovieRemoteDataSource(movieAPIService)
    }.flow.cachedIn(viewModelScope)
}