package com.ceresdroidxapps.taskapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


interface PaginationItem

@Parcelize
data class MovieItem(
    val id: String? = null,
    val title: String? = null,
    val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
   @SerializedName("release_date") val releaseDate: String? = null,
   @SerializedName("vote_average") val voteAverage: Double? = null
): Parcelable, PaginationItem



object LoaderView: PaginationItem