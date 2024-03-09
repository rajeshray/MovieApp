package com.ceresdroidxapps.taskapp.views

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import com.ceresdroidxapps.taskapp.base.BaseActivity
import com.ceresdroidxapps.taskapp.databinding.ActivityMainBinding
import com.ceresdroidxapps.taskapp.utils.changeIconToMain
import com.ceresdroidxapps.taskapp.utils.changeToSecondIcon
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initUI() {
        setSupportActionBar(binding.toolbar)

        binding.secondIconButton.setOnClickListener {
            changeToSecondIcon(this@MainActivity)
        }
        binding.mainIconButton.setOnClickListener {
            changeIconToMain(this@MainActivity)
        }
    }

    override fun initListeners() {

    }

    override fun initObservers() {

    }
}