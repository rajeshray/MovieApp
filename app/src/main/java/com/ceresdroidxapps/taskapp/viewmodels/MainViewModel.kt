package com.ceresdroidxapps.taskapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceresdroidxapps.taskapp.data.model.MovieItem
import com.ceresdroidxapps.taskapp.data.model.MoviePaginationItem
import com.ceresdroidxapps.taskapp.data.model.PaginationItem
import com.ceresdroidxapps.taskapp.data.network.Resource
import com.ceresdroidxapps.taskapp.data.network.Status
import com.ceresdroidxapps.taskapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val movieListLiveData = MutableLiveData<Resource<List<MovieItem>>>()

    val movieList = mutableListOf<PaginationItem>()

    var totalMovies = 0
    var currentPage = 1

    init {
        movieListLiveData.postValue(Resource(Status.LOADING, null, null))
        getAllMovies()
    }

    fun getAllMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchMovies()
            }
        }
    }

    private fun fetchMovies() {

        val call = mainRepository.getPopularMoviesList(
            pageNumber = currentPage
        )
        call.enqueue(object : Callback<MoviePaginationItem> {
            override fun onResponse(
                call: Call<MoviePaginationItem>,
                response: Response<MoviePaginationItem>
            ) {
                totalMovies = response.body()?.totalResult ?: 0

                movieListLiveData.postValue(
                    Resource(
                        Status.SUCCESS,
                        response.body()?.movieList,
                        response.isSuccessful.toString()
                    )
                )
            }

            override fun onFailure(call: Call<MoviePaginationItem>, t: Throwable) {
                movieListLiveData.postValue(Resource(Status.ERROR, null, t.message))
            }

        })
    }
}