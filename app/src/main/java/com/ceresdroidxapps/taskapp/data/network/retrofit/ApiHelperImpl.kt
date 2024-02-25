package com.ceresdroidxapps.taskapp.data.network.retrofit


import com.ceresdroidxapps.taskapp.data.model.MovieItem
import com.ceresdroidxapps.taskapp.data.model.MoviePaginationItem
import retrofit2.Call
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {


    override fun getPopularMoviesList(
        pageNumber: Int,
        includeAdult: Boolean,
        includeVideo: Boolean,
        sortBy: String
    ): Call<MoviePaginationItem> {
        return apiService.getPopularMoviesList(pageNumber = pageNumber)
    }
}
