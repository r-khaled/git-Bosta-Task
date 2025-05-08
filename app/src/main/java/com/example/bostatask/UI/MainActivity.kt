package com.example.bostatask.UI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bostatask.UI.screens.CityListScreen
import com.example.bostatask.UI.screens.SplashScreen
import com.example.bostatask.data.remote.BostaApiService
import com.example.bostatask.repository.BostaRepository
import com.example.bostatask.viewmodel.CitiesViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://stg-app.bosta.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(BostaApiService::class.java)

        val repository = BostaRepository(apiService)
        val viewModel = CitiesViewModel(repository)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen {
                        navController.navigate("cityList") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                }
                composable("cityList") {
                    CityListScreen(viewModel)
                }
            }
        }
    }
}


