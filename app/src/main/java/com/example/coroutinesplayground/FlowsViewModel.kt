package com.example.coroutinesplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch

class FlowsViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow("")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun triggerStateFlow() {
        _stateFlow.value = "State Flow Triggered"
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(5) {
                emit("$it $")
                delay(1000L)
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("Shared Flow Error Message")
        }
    }
}