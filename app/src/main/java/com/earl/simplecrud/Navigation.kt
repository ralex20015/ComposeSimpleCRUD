package com.earl.simplecrud

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.earl.simplecrud.Destinations.ADMIN_HOME
import com.earl.simplecrud.Destinations.HOME
import com.earl.simplecrud.Destinations.SIGN_IN_ROUTE
import com.earl.simplecrud.Destinations.SIGN_UP_ROUTE
import com.earl.simplecrud.Destinations.USER_HOME
import com.earl.simplecrud.db.UserRepository
import com.earl.simplecrud.screens.AdminRoute
import com.earl.simplecrud.screens.SignInRoute
import com.earl.simplecrud.screens.SignUpRoute

object Destinations {
    const val SIGN_UP_ROUTE = "signUp"
    const val SIGN_IN_ROUTE = "signIn"
    const val HOME = "home"
    const val ADMIN_HOME = "adminHome"
    const val USER_HOME = "userHome"
}


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = SIGN_IN_ROUTE) {
        composable(SIGN_UP_ROUTE) {
            SignUpRoute(onRegisterSubmitted = navController::navigateUp, navController::navigateUp)
        }
        //Creo que aqui me conviene mejor hacer un unico viewmodel
        composable(SIGN_IN_ROUTE) {
            SignInRoute(
                onSignUpSubmitted = {
                    navController.navigate(SIGN_UP_ROUTE)
                },
                onSignInSubmitted ={ currentUser ->
                    if(currentUser == UserRepository.Usuario.AdminUser){
                        navController.navigate(ADMIN_HOME)
                    }
                }
            )
        }

        navigation(route = HOME, startDestination = ADMIN_HOME) {

            //TODO: Make the ViewModel for the admin and user
            composable(ADMIN_HOME) {
                AdminRoute()
            }

            composable(USER_HOME) {

            }
        }

    }
}