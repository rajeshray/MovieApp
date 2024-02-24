package com.ceresdroidxapps.taskapp.views

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceresdroidxapps.taskapp.R
import com.ceresdroidxapps.taskapp.base.BaseActivity
import com.ceresdroidxapps.taskapp.data.model.ProjectItemModel
import com.ceresdroidxapps.taskapp.data.network.Resource
import com.ceresdroidxapps.taskapp.data.network.Status
import com.ceresdroidxapps.taskapp.databinding.ActivityMainBinding
import com.ceresdroidxapps.taskapp.utils.hide
import com.ceresdroidxapps.taskapp.utils.show
import com.ceresdroidxapps.taskapp.viewmodels.MainViewModel
import com.ceresdroidxapps.taskapp.adapters.ProjectItemClickListener
import com.ceresdroidxapps.taskapp.adapters.ProjectListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    ProjectItemClickListener {

    private val mainViewModel: MainViewModel by viewModels()
    private var listOfProjects = ArrayList<ProjectItemModel>()

    override fun initUI() {
        binding.searchLayoutContainer.searchContainer.setOnClickListener {
            openSearchActivity()
        }
    }

    private fun openSearchActivity() {
        val intent = Intent(this, SearchActivity::class.java)
        intent.putParcelableArrayListExtra(INTENT_DATA_PROJECT_LIST, listOfProjects)
        startActivity(intent)
    }

    override fun initListeners() {

    }

    override fun initObservers() {
        mainViewModel.listOfAllProjects.observe(this) {
            if (it != null) {
                 setUpView(it)
            }
        }
    }

    private fun setUpView(resource: Resource<List<ProjectItemModel>>) {
        when (resource.status) {
            Status.ERROR -> {
                binding.lottie.setAnimation(R.raw.bot_error)
                binding.lottie.playAnimation()
                binding.errorContainer.show()
                binding.loader.hide()
                binding.recyclerView.hide()
                binding.searchLayoutContainer.searchContainer.hide()
            }

            Status.SUCCESS -> {
                binding.errorContainer.hide()
                binding.loader.hide()
                binding.recyclerView.show()
                binding.searchLayoutContainer.searchContainer.show()
                setUpAdapter(resource.data!!)
            }

            Status.LOADING -> {
                binding.errorContainer.hide()
                binding.loader.show()
                binding.recyclerView.hide()
                binding.searchLayoutContainer.searchContainer.hide()
            }
        }
    }

    private fun setUpAdapter(data: List<ProjectItemModel>) {
        listOfProjects.addAll(data)
        val adapter = ProjectListAdapter(this,
        data,
        this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClickItem(item: ProjectItemModel) {

    }
}