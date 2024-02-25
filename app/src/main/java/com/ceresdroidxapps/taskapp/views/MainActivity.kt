package com.ceresdroidxapps.taskapp.views

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceresdroidxapps.taskapp.R
import com.ceresdroidxapps.taskapp.adapters.MovieItemClickListener
import com.ceresdroidxapps.taskapp.adapters.MovieListAdapter
import com.ceresdroidxapps.taskapp.base.BaseActivity
import com.ceresdroidxapps.taskapp.data.model.MovieItem
import com.ceresdroidxapps.taskapp.data.network.Resource
import com.ceresdroidxapps.taskapp.data.network.Status
import com.ceresdroidxapps.taskapp.databinding.ActivityMainBinding
import com.ceresdroidxapps.taskapp.utils.PagingScrollListener
import com.ceresdroidxapps.taskapp.utils.hide
import com.ceresdroidxapps.taskapp.utils.show
import com.ceresdroidxapps.taskapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MovieItemClickListener {

    private val viewModel: MainViewModel by viewModels()

    private var isLoading = false
    private var movieListAdapter: MovieListAdapter? = null

    override fun initUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Movie list"
        setUpAdapter()
    }

    override fun initListeners() {

    }

    override fun initObservers() {
        viewModel.apply {
            movieListLiveData.observe(this@MainActivity) { list ->

                if (!movieList.isNullOrEmpty()) {
                    isLoading = false
                    movieListAdapter?.removeLoadingView()
                }

                if (list != null) {
                    setUpView(list)
                }
            }
        }
    }

    private fun setUpView(resource: Resource<List<MovieItem>>) {
        when (resource.status) {
            Status.ERROR -> {
                binding.lottie.setAnimation(R.raw.bot_error)
                binding.lottie.playAnimation()
                binding.loader.hide()
                binding.rv.hide()
            }

            Status.SUCCESS -> {
                binding.loader.hide()
                binding.rv.show()
                addDataToMovieList(resource.data)
            }

            Status.LOADING -> {
                binding.loader.show()
                binding.rv.hide()
            }
        }
    }

    private fun addDataToMovieList(data: List<MovieItem>?) {
       if (!data.isNullOrEmpty()) {
           movieListAdapter?.addAll(data)
       }
    }


    private fun setUpAdapter() {

        movieListAdapter = MovieListAdapter(
            this,
            viewModel.movieList,
            this
        )

        val layoutManager = LinearLayoutManager(this)


        val scrollListener = object : PagingScrollListener(layoutManager) {

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems(totalItems: Int) {

                if (totalItems < viewModel.totalMovies) {
                    isLoading = true
                    movieListAdapter?.addLoadingView()
                    viewModel.apply {
                        currentPage++
                        getAllMovies()
                    }
                }
            }
        }


        binding.rv.apply {
            this.adapter = movieListAdapter
            this.layoutManager = layoutManager
            addOnScrollListener(scrollListener)
        }
    }

    override fun onClickItem(item: MovieItem?) {
        item?.let {
            val detailsActivityIntent = DetailsActivity.createIntent(this, it)
            startActivity(detailsActivityIntent)
        }
    }
}