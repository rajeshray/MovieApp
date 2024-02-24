package com.ceresdroidxapps.taskapp.data.repository

import com.ceresdroidxapps.taskapp.data.model.ProjectItemModel
import com.ceresdroidxapps.taskapp.data.network.retrofit.ApiHelper
import com.ceresdroidxapps.taskapp.data.network.retrofit.ApiHelperImpl
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiHelperImpl: ApiHelperImpl
): ApiHelper {

    override fun getAllProjects(): Call<List<ProjectItemModel>> {
        return apiHelperImpl.getAllProjects()
    }

}