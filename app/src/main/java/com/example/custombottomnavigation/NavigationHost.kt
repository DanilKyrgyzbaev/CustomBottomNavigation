package com.example.custombottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.custombottomnavigation.ItemsMenu.*

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home.ruta) {
        composable(Home.ruta) {
            Home()
        }
        composable(Pets.ruta) {
            Pets()
        }
        composable(Stars.ruta) {
            Stars()
        }
        composable(Books.ruta) {
            Books()
        }

        composable(Profile.ruta) {
            Profile()
        }
    }
}
