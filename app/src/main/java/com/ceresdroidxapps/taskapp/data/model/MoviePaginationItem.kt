package com.ceresdroidxapps.taskapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviePaginationItem(
    val page: Int,
    @SerializedName("results") val movieList: List<MovieItem> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResult: Int
)
