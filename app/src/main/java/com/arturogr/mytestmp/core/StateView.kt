package com.arturogr.mytestmp.core

sealed class StateView(val stateView: String) {
    data object Init : StateView("init")
    data object Loading : StateView("loading")
    data object Success : StateView("success")
    data object Error : StateView("error") {
        fun setMessage(message: String) = message
    }
}