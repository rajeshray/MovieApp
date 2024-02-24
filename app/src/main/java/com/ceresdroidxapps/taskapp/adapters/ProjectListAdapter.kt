package com.ceresdroidxapps.taskapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ceresdroidxapps.taskapp.R
import com.ceresdroidxapps.taskapp.data.model.ProjectItemModel
import com.ceresdroidxapps.taskapp.databinding.FrappRvItemBinding

class ProjectListAdapter(
    private val context: Context,
    private val projectList: List<ProjectItemModel>,
    private val projectItemClickListener: ProjectItemClickListener
): RecyclerView.Adapter<ProjectListAdapter.ProjectItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.frapp_rv_item, parent, false)

        return ProjectItemViewHolder(view)

    }


    override fun onBindViewHolder(holder: ProjectItemViewHolder, position: Int) {
        Glide.with(context)
            .load(projectList[position].logo)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(4)))
            .into(holder.binding.ivThumbnail)

        holder.binding.tvTitle.text = projectList[position].title
        holder.binding.tvSubTitle.text = projectList[position].description
        holder.binding.tvEarning.text = "Earnings: ₹${projectList[position].earning}/ spoken minute"
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    inner class ProjectItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = FrappRvItemBinding.bind(view)
        init {
            binding.root.setOnClickListener {
                projectItemClickListener.onClickItem(projectList[adapterPosition])
            }
        }
    }
}

interface ProjectItemClickListener {

    fun onClickItem(item: ProjectItemModel)

}