package com.arturogr.mytestmp.presentation

sealed class Routes(val route: String) {
    data object Login: Routes("login")
    data object SignUp: Routes("signup")
    data object Home: Routes("home")
    data object Detail: Routes("detail")
}