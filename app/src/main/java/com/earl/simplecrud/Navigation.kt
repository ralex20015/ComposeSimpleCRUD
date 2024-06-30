package com.earl.simplecrud

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.earl.simplecrud.Destinations.SIGN_IN_ROUTE
import com.earl.simplecrud.Destinations.SIGN_UP_ROUTE
import com.earl.simplecrud.screens.SignUpRoute

object Destinations {
    const val SIGN_UP_ROUTE = "signUp"
    const val SIGN_IN_ROUTE = "signIn"
}


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = SIGN_IN_ROUTE) {
        composable(SIGN_UP_ROUTE) {
            SignUpRoute()
        }
        composable(SIGN_IN_ROUTE) {

        }

    }
}