package com.ceresdroidxapps.taskapp.utils

object Constants {
    const val SHARED_PREF_NAME = "task_app_pref"
    const val DATABASE_NAME  = "task_app_db"
    const val DATABASE_VERSION = 1

    const val BASE_URL = " https://us-central1-frapp-prod.cloudfunctions.net"
    const val GET_PROJECTS = "$BASE_URL/gcf-mock/projects"
}