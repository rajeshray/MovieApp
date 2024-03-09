package com.ceresdroidxapps.taskapp.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

inline fun <reified T> Intent.getParcelableModel(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelableExtra(key, T::class.java)
    } else {
        this.getParcelableExtra(key)
    }
}


fun Activity.changeIconToMain(context: Context) {
    try {
        // Get the package manager instance
        val packageManager = context.packageManager

        val secondIconComponent = ComponentName(context, "com.ceresdroidxapps.taskapp.SecondIcon")
        packageManager.setComponentEnabledSetting(
            secondIconComponent,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        // Enable the main activity component
        val defaultIcon = ComponentName(context, "com.ceresdroidxapps.taskapp.DefaultIcon")
        packageManager.setComponentEnabledSetting(
            defaultIcon,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        // Log and display a toast message indicating that the main logo is enabled
        Toast.makeText(context.applicationContext, "Main Logo Enabled.", Toast.LENGTH_SHORT)
            .show()
    } catch (e: java.lang.Exception) {
        Log.e("Exception", e.toString())
    }
}

fun Activity.changeToSecondIcon(context: Context) {
    try {
        // Get the package manager instance
        val packageManager = context.packageManager

        // Enable the main activity component
        val defaultIcon = ComponentName(context, "com.ceresdroidxapps.taskapp.DefaultIcon")
        packageManager.setComponentEnabledSetting(
            defaultIcon,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        val secondIconComponent = ComponentName(context, "com.ceresdroidxapps.taskapp.SecondIcon")
        packageManager.setComponentEnabledSetting(
            secondIconComponent,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        // Log and display a toast message indicating that the main logo is enabled
        Toast.makeText(context.applicationContext, "Second Logo Enabled.", Toast.LENGTH_SHORT)
            .show()
    } catch (e: java.lang.Exception) {
        Log.e("Exception", e.toString())
    }
}