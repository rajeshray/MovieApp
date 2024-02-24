package com.ceresdroidxapps.taskapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceresdroidxapps.taskapp.data.model.ProjectItemModel
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
): ViewModel() {

    val listOfAllProjects = MutableLiveData<Resource<List<ProjectItemModel>>>()

    init {
        listOfAllProjects.postValue(Resource(Status.LOADING, null, null))
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchAllProjects()
            }
        }
    }

    private fun fetchAllProjects() {

        val call = mainRepository.getAllProjects()
        call.enqueue(object: Callback<List<ProjectItemModel>>{
            override fun onResponse(
                call: Call<List<ProjectItemModel>>,
                response: Response<List<ProjectItemModel>>
            ) {
                listOfAllProjects.postValue(Resource(Status.SUCCESS, response.body(), response.isSuccessful.toString()))
            }

            override fun onFailure(call: Call<List<ProjectItemModel>>, t: Throwable) {
                listOfAllProjects.postValue(Resource(Status.ERROR, null, t.message))
            }

        })
    }
}