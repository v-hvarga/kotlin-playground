package com.example.coroutinesplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch

class FlowsActivity : AppCompatActivity() {

    private val viewModel: FlowsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flows)

        val stateFlowButton = findViewById<Button>(R.id.btn_state_flow)
        stateFlowButton.setOnClickListener {
            viewModel.triggerStateFlow()
        }

        val flowButton = findViewById<Button>(R.id.btn_flow)
        flowButton.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                viewModel.triggerFlow().collectLatest {
                    val flowTextView = findViewById<TextView>(R.id.tv_flow)
                    flowTextView.text = it
                }
            }
        }

        val sharedFlowButton = findViewById<Button>(R.id.btn_shared_flow)
        sharedFlowButton.setOnClickListener {
            viewModel.triggerSharedFlow()
        }

        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.launchWhenStarted {
            /*viewModel.stateFlow.collectLatest {
                val textStateFlow = findViewById<TextView>(R.id.tv_state_flow)
                textStateFlow.text = it
                Snackbar.make(window.decorView.rootView, it, Snackbar.LENGTH_LONG).show()
            }*/
            viewModel.sharedFlow.collectLatest {
                Snackbar.make(window.decorView.rootView, it, Snackbar.LENGTH_LONG).show()
            }

        }

    }
}