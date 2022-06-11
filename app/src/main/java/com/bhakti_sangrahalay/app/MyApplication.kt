package com.bhakti_sangrahalay

import android.app.Application
import android.content.Context
import com.bhakti_sangrahalay.repository.DataHoler

class MyApplication:Application() {
    public lateinit var dataHoler:DataHoler;
    /*init {
        instance = this
    }*/

    companion object {
        private lateinit var instance: MyApplication;

        fun applicationContext() : MyApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance=this;
        dataHoler= DataHoler();
    }
}