package com.ceresdroidxapps.taskapp.data.network.interfaces

import com.ceresdroidxapps.taskapp.data.model.MoviePaginationItem
import com.ceresdroidxapps.taskapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectApiHelper {

    @GET("${Constants.BASE_URL}discover/movie?api_key=${Constants.TMDB_API_KEY}")
    fun getPopularMoviesList(
        @Query("page") pageNumber: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Call<MoviePaginationItem>

}