package com.arturogr.mytestmp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arturogr.mytestmp.core.Constants
import com.arturogr.mytestmp.domain.model.DetailMovie
import com.arturogr.mytestmp.presentation.ui.detail.DetailScreen
import com.arturogr.mytestmp.presentation.ui.home.HomeScreen
import com.arturogr.mytestmp.presentation.ui.login.LoginScreen
import com.arturogr.mytestmp.presentation.ui.signup.SignUpScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                onNext = {
                    navController.navigate(route = Routes.Home.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                    }
                },
                onSignUp = {
                    navController.navigate(route = Routes.SignUp.route)
                }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onNext = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        Constants.KEY_BUNDLE,
                        it
                    )
                    navController.navigate(route = Routes.Detail.route)
                },
                onLogout = {
                    Firebase.auth.signOut()
                    navController.navigate(route = Routes.Login.route) {
                        popUpTo(Routes.Home.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.SignUp.route) {
            SignUpScreen(onNext = {
                navController.navigate(route = Routes.Home.route) {
                    popUpTo(Routes.Login.route) {
                        inclusive = true
                    }
                }
            }, onBack = {
                navController.popBackStack()
            })
        }

        composable(Routes.Detail.route) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<DetailMovie>(
                Constants.KEY_BUNDLE
            )
            DetailScreen(movie = result, onBack = {
                navController.popBackStack()
            })
        }
    }
}
