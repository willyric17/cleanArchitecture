package com.example.joblisting.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joblisting.R
import com.example.joblisting.internal.entity.JobEntity
import kotlinx.android.synthetic.main.item_jobs.view.*

class JobAdapter : RecyclerView.Adapter<JobViewHolder>() {

    var jobs: List<JobEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(View.inflate(parent.context, R.layout.item_jobs, null))
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(jobs[position])
    }
}

class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(entity: JobEntity) {
        itemView.apply {
            origin.text = entity.origin
            destination.text = entity.destination
            price.text = "Rp. ${entity.price}"
            date.text = "date : ${entity.date}"
        }
    }
}