package com.satyam.movielist.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.satyam.movielist.databinding.ItemMovieListBinding
import com.satyam.movielist.model.Search
import java.util.*

private const val TYPE_PROGRESS = 0
private const val TYPE_ITEM = 1

class MovieListAdapter : PagingDataAdapter<Search, MovieListAdapter.MovieItemViewHolder>(DataComparator) {

    private val rnd = Random()

    class MovieItemViewHolder(val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(ItemMovieListBinding.inflate(LayoutInflater.from(parent.context)))



    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.binding.movieData = getItem(position)
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.binding.cardView.setCardBackgroundColor(color)
    }

    object DataComparator : DiffUtil.ItemCallback<Search>() {

        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.Title == newItem.Title
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }
    }

}
