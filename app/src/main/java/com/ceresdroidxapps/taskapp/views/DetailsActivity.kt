package com.ceresdroidxapps.taskapp.views

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.ceresdroidxapps.taskapp.base.BaseActivity
import com.ceresdroidxapps.taskapp.data.model.MovieItem
import com.ceresdroidxapps.taskapp.databinding.ActivityDetailsBinding
import com.ceresdroidxapps.taskapp.utils.Constants
import com.ceresdroidxapps.taskapp.utils.getParcelableModel
import com.ceresdroidxapps.taskapp.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity<ActivityDetailsBinding>(ActivityDetailsBinding::inflate) {

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun initUI() {
        detailsViewModel.movieDetailItem.value = intent.getParcelableModel<MovieItem>(MOVIE_ITEM)
    }

    override fun initListeners() {
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun initObservers() {
        detailsViewModel.apply {
            movieDetailItem.observe(this@DetailsActivity) {
                setUpView(it)
            }
        }
    }

    private fun setUpView(movieItem: MovieItem?) {
        movieItem?.let {
            binding.tvSubTitle.text = it.overview

            Glide.with(this)
                .load("${Constants.TMDB_IMAGE_URL}/${it.posterPath}")
                .into(binding.ivThumbnail)
        }
    }

    companion object {

        private const val MOVIE_ITEM = "movieItem"

        fun createIntent(context: Context, movieItem: MovieItem) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(MOVIE_ITEM, movieItem)
            }
    }

}