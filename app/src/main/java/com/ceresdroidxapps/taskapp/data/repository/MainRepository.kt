package com.ceresdroidxapps.taskapp.data.repository

import com.ceresdroidxapps.taskapp.data.model.MovieItem
import com.ceresdroidxapps.taskapp.data.model.MoviePaginationItem
import com.ceresdroidxapps.taskapp.data.network.retrofit.ApiHelper
import com.ceresdroidxapps.taskapp.data.network.retrofit.ApiHelperImpl
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiHelperImpl: ApiHelperImpl
): ApiHelper {


    override fun getPopularMoviesList(
        pageNumber: Int,
        includeAdult: Boolean,
        includeVideo: Boolean,
        sortBy: String
    ): Call<MoviePaginationItem> {
        return apiHelperImpl.getPopularMoviesList(pageNumber = pageNumber)
    }

}