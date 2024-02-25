package com.ceresdroidxapps.taskapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ceresdroidxapps.taskapp.data.model.LoaderView
import com.ceresdroidxapps.taskapp.data.model.MovieItem
import com.ceresdroidxapps.taskapp.data.model.PaginationItem
import com.ceresdroidxapps.taskapp.databinding.LoaderViewBinding
import com.ceresdroidxapps.taskapp.databinding.MovieListItemBinding
import com.ceresdroidxapps.taskapp.utils.Constants

class MovieListAdapter(
    private val context: Context,
    private val movieItemList: MutableList<PaginationItem>,
    private val movieItemClickListener: MovieItemClickListener
) : RecyclerView.Adapter<ViewHolder>() {

    private var movieItemFullList = ArrayList(movieItemList)

    companion object {
        const val TYPE_MOVIE = 1
        const val TYPE_LOADER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_MOVIE -> MovieItemViewHolder(
                MovieListItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            TYPE_LOADER -> LoadingViewHolder(
                LoaderViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("View type not found: ${viewType.javaClass.name}")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return when (getItemViewType(position)) {
            TYPE_MOVIE -> (holder as MovieItemViewHolder).bind(movieItemList[position] as MovieItem)
            TYPE_LOADER -> (holder as LoadingViewHolder).bind(movieItemList[position] as LoaderView)
            else -> throw IllegalArgumentException("Cannot bind view holder. Illegal type")
        }

    }


    override fun getItemCount(): Int {
        return movieItemList.size
    }

    override fun getItemViewType(position: Int): Int {

        return if (movieItemList.isNotEmpty()) {
            when (movieItemList[position]) {
                is MovieItem -> TYPE_MOVIE
                is LoaderView -> TYPE_LOADER
                else -> {
                    Log.d("VIEW_TYPE", movieItemList[position].toString())
                    RecyclerView.NO_POSITION
                }
            }
        } else {
            throw java.lang.IllegalArgumentException("Cannot pass viewType as list is empty")
        }
    }

    fun addLoadingView() {
        add(LoaderView)
    }

    fun removeLoadingView() {
        val position = movieItemList.lastIndex
        val cell = movieItemList[position]

        if (cell is LoaderView) {
            movieItemList.remove(cell)
            notifyItemRemoved(position)
        }
    }

    private fun add(item: PaginationItem) {
        movieItemList.add(item)
        notifyItemInserted(movieItemList.lastIndex)
    }

    fun addAll(list: List<PaginationItem>) {
        val startPosition = movieItemList.size
        movieItemList.addAll(list)
        notifyItemRangeInserted(startPosition, list.size)
        movieItemFullList = ArrayList(movieItemList)

    }

    fun clear() {
        val size = movieItemList.size
        movieItemList.clear()
        notifyItemRangeRemoved(0, size)
        movieItemFullList = ArrayList(movieItemList)
    }

    inner class MovieItemViewHolder(private val binding: MovieListItemBinding) :
        ViewHolder(binding.root) {

        fun bind(movieItem: MovieItem?) {
            binding.root.setOnClickListener {
                movieItemClickListener.onClickItem(movieItem)
            }
            movieItem?.let {
                binding.tvTitle.text = it.title
                binding.tvSubTitle.text = it.overview

                Glide.with(context)
                    .load("${Constants.TMDB_IMAGE_URL}/${movieItem.backdropPath}")
                    .centerCrop()
                    .into(binding.ivThumbnail)
            }
        }
    }

    inner class LoadingViewHolder(private val binding: LoaderViewBinding) :
        ViewHolder(binding.root) {
        fun bind(loaderView: LoaderView?) {
            binding.executePendingBindings()
        }
    }
}

interface MovieItemClickListener {
    fun onClickItem(item: MovieItem?)
}