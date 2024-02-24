package com.ceresdroidxapps.taskapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

typealias  ActivityInflate<T>  = (LayoutInflater) -> T

abstract class BaseActivity<VB: ViewBinding> (
    private val inflate: ActivityInflate<VB>
): AppCompatActivity() {

    abstract fun initUI()

    abstract fun initListeners()

    abstract fun initObservers()

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListeners()
        initObservers()
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}