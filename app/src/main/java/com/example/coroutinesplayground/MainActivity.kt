package com.example.coroutinesplayground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG = "XQT"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        GlobalScope.launch {
            while (true) {
                delay(1000)
                Log.d(TAG, "Globalscope running...")
            }
        }

        GlobalScope.launch {
            delay(3500)
            Intent(this@MainActivity, SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
*/
        val btn = findViewById<Button>(R.id.btn_flows)
        btn.setOnClickListener {
            Intent(this@MainActivity, FlowsActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}