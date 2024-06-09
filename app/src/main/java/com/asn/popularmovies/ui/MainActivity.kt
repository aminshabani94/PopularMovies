package com.asn.popularmovies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.asn.popularmovies.navigation.SetupNavigation
import com.asn.popularmovies.ui.theme.PopularMoviesTheme
import com.asn.popularmovies.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private val viewModel: SharedViewModel by viewModels()

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopularMoviesTheme {
                navHostController = rememberNavController()
                SetupNavigation(
                    navController = navHostController,
                    sharedViewModel = viewModel
                )
            }
        }
    }
}