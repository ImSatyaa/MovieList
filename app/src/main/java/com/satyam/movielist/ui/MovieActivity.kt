package com.satyam.movielist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.satyam.movielist.databinding.MovieActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var mViewModel :MovieActivityViewModel

    private lateinit var mMovieListAdapter : MovieListAdapter

    private lateinit var mBinding : MovieActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = MovieActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this).get(MovieActivityViewModel::class.java)

        mMovieListAdapter = MovieListAdapter()


        mBinding.rvMovieList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mMovieListAdapter

            adapter = mMovieListAdapter.withLoadStateFooter(
                footer = MovieLoadAdapter { mMovieListAdapter.retry() }
            )
        }

    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            mViewModel.movieList.collectLatest {
                mMovieListAdapter.submitData(it)
            }
        }
    }
}
