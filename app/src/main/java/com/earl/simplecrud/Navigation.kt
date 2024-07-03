package com.earl.simplecrud

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.earl.simplecrud.Destinations.ADMIN_HOME
import com.earl.simplecrud.Destinations.AUTH
import com.earl.simplecrud.Destinations.HOME
import com.earl.simplecrud.Destinations.SIGN_IN_ROUTE
import com.earl.simplecrud.Destinations.SIGN_UP_ROUTE
import com.earl.simplecrud.Destinations.USER_HOME
import com.earl.simplecrud.home.AdminRoute
import com.earl.simplecrud.signinsignup.SignInRoute
import com.earl.simplecrud.signinsignup.SignInViewModel
import com.earl.simplecrud.signinsignup.SignInViewModelFactory
import com.earl.simplecrud.signinsignup.SignUpRoute
import com.earl.simplecrud.signinsignup.UserType

object Destinations {
    const val AUTH = "auth"
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
    val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory(LocalContext.current.applicationContext as MyApplication))
    val userLoggedIn = signInViewModel.sessionState.collectAsState().value

    NavHost(navController = navController,   startDestination = if (userLoggedIn.isLoggedIn) HOME else AUTH) {
        navigation(route= AUTH, startDestination = SIGN_IN_ROUTE){
            composable(SIGN_UP_ROUTE) {
                SignUpRoute(onRegisterSubmitted = { isRegistered ->
                    if(isRegistered) {
                        navController.navigateUp()
                    }
                }, navController::navigateUp)
            }

            composable(SIGN_IN_ROUTE) {
                SignInRoute(
                    signInViewModel = signInViewModel,
                    onSignUpSubmitted = {
                        navController.navigate(SIGN_UP_ROUTE)
                    },
                    onSignInSubmitted = { currentUser ->
                        if(currentUser == UserType.ADMIN){
                            navController.navigate(HOME){
                                popUpTo(AUTH) {
                                    inclusive = true
                                }
                            }
                        }
                        if(currentUser == UserType.USER){
                            navController.navigate(USER_HOME)
                        }
                    }
                )
            }
        }

        navigation(route = HOME, startDestination = ADMIN_HOME) {
            //TODO: Make the ViewModel for the admin and user
            composable(ADMIN_HOME) {
                val context = LocalContext.current
                AdminRoute(
                  onLogoutSubmitted = {
                      signInViewModel.logout(context)
                      navController.navigate(AUTH){
                          popUpTo(HOME) {
                              inclusive = true
                          }
                      }
                  }
                )
            }

            composable(USER_HOME) {

            }
        }

    }
}