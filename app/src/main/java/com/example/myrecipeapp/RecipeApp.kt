package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun RecipeApp(
    navController: NavHostController
) {
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoryState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, navigateToDetail = {
                // save category
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(Screen.DetailScreen.route) {
            // get category
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category") ?: Category(
                idCategory = "0",
                strCategory = "Unknown",
                strCategoryThumb = "",
                strCategoryDescription = "Unknown"
            )
            CategoryDetailsScreen(category = category)
        }
    }
}