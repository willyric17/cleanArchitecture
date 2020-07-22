package com.example.joblisting

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joblisting.adapter.JobAdapter
import com.example.joblisting.injection.JobInjection
import com.example.joblisting.viewmodel.JobListViewModel
import kotlinx.android.synthetic.main.activity_joblist.*

class JobListActivity : AppCompatActivity() {
    lateinit var viewmodel: JobListViewModel

    val adapter by lazy { JobAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_joblist)

        JobInjection.inject(this)

        viewmodel.getJobsLiveData().observe(this, Observer {
            adapter.jobs = it
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> viewmodel.displayNewest()
                    1 -> viewmodel.displayOldest()
                    2 -> viewmodel.displayLowestPrice()
                    else -> viewmodel.displayHighestPrice()
                }

            }

        }

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}