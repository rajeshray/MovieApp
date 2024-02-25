package com.ceresdroidxapps.taskapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceresdroidxapps.taskapp.data.model.MovieItem
import com.ceresdroidxapps.taskapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

     val movieDetailItem = MutableLiveData<MovieItem?>()
}