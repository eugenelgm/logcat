package com.eugene.logcatdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugene.logcatlib.Logcat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logcat.debug("MainActivity Started!")
    }
}