package com.example.joblisting

import android.app.Application
import com.example.joblisting.injection.JobInjection

class JobApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        JobInjection.initialize()
    }
}