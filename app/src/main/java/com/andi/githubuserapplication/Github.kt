package com.andi.githubuserapplication

import androidx.multidex.MultiDexApplication

class Github:MultiDexApplication() {
    companion object{
        lateinit var instance:Github

        fun getApp():Github{
            return instance
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}