package com.ceresdroidxapps.taskapp.data.network.interfaces

import com.ceresdroidxapps.taskapp.data.model.ProjectItemModel
import com.ceresdroidxapps.taskapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ProjectApiHelper {

    @GET(Constants.GET_PROJECTS)
    fun getAllProjects(): Call<List<ProjectItemModel>>

}