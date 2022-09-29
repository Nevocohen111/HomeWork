package com.example.hackeruapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class NotesService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationsManager.getServiceNotification(this)
        startForeground(1, notification)
        myServiceFunction()
        return super.onStartCommand(intent, flags, startId)

    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun myServiceFunction() {
        thread(start = true) {
            while (true) {
                Log.d("Test", "myServiceFunction: 5 seconds log")
                Thread.sleep(5000)
            }
        }
    }
}