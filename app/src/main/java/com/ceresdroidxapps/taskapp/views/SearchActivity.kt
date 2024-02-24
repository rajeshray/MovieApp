package com.ceresdroidxapps.taskapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.view.isGone
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceresdroidxapps.taskapp.R
import com.ceresdroidxapps.taskapp.adapters.ProjectItemClickListener
import com.ceresdroidxapps.taskapp.adapters.ProjectListAdapter
import com.ceresdroidxapps.taskapp.base.BaseActivity
import com.ceresdroidxapps.taskapp.data.model.ProjectItemModel
import com.ceresdroidxapps.taskapp.databinding.ActivitySearchBinding
import com.ceresdroidxapps.taskapp.utils.hide
import com.ceresdroidxapps.taskapp.utils.show
import java.util.*
import kotlin.collections.ArrayList

const val INTENT_DATA_PROJECT_LIST = "intent_data_project_list"

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),
    ProjectItemClickListener {

    private var projectList: ArrayList<ProjectItemModel>? = ArrayList()

    override fun initUI() {
        projectList = intent.extras?.getParcelableArrayList(INTENT_DATA_PROJECT_LIST)
        binding.etSearch.requestFocus()
        binding.etSearch.showSoftInputOnFocus
    }

    override fun initListeners() {
        binding.ibClearSearch.setOnClickListener {
            clearSearch()
        }

        binding.ibBackBtn.setOnClickListener {
            onBackPressed()
        }

        binding.etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    runSearchQuery(p0.toString().trim().lowercase())

                } else {
                    binding.recyclerView.hide()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }


    private fun runSearchQuery(searchQuery: String) {
       val filteredProjectList = ArrayList<ProjectItemModel>()

        projectList?.forEach {
            if (it.title!!.lowercase().contains(searchQuery) ||
                it.earning.toString().contains(searchQuery) ||
                    it.description!!.lowercase().contains(searchQuery)) {
                filteredProjectList.add(it)
            }
        }

        setUpScreen(filteredProjectList)

    }

    private fun setUpScreen(filteredProjectList: ArrayList<ProjectItemModel>) {
        if (filteredProjectList.isEmpty()) {
            binding.recyclerView.hide()
            binding.errorContainer.show()

        } else {
            binding.recyclerView.show()
            binding.errorContainer.hide()
            setAdapter(filteredProjectList)
        }
    }

    private fun setAdapter(filteredProjectList: ArrayList<ProjectItemModel>) {
        val adapter = ProjectListAdapter(this,
            filteredProjectList.toList(),
            this
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
    }

    private fun clearSearch() {
        binding.recyclerView.hide()
        binding.etSearch.setText("")
    }

    override fun initObservers() {

    }

    override fun onClickItem(item: ProjectItemModel) {

    }

}