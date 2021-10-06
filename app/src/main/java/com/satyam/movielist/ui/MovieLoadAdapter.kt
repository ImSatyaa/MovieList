package com.satyam.movielist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.satyam.movielist.databinding.LoadStateViewBinding

class MovieLoadAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadAdapter.LoadingViewHolder>() {

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {


        holder.binding.btnRetry.isVisible = loadState !is LoadState.Loading
        holder.binding.tvErrorMsg.isVisible = loadState !is LoadState.Loading
        holder.binding.progressCircular.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error){
            holder.binding.tvErrorMsg.text = loadState.error.localizedMessage
        }

        holder.binding.btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingViewHolder(LoadStateViewBinding.inflate(LayoutInflater.from(parent.context)))


    class LoadingViewHolder(val binding: LoadStateViewBinding) : RecyclerView.ViewHolder(binding.root)
}