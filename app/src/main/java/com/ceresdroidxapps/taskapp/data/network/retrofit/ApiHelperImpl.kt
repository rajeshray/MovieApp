package com.ceresdroidxapps.taskapp.data.network.retrofit


import com.ceresdroidxapps.taskapp.data.model.ProjectItemModel
import retrofit2.Call
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {

    override fun getAllProjects(): Call<List<ProjectItemModel>> {
        return apiService.getAllProjects()
    }

}
