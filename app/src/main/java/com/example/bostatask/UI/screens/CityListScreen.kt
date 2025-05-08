package com.example.bostatask.UI.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bostatask.viewmodel.CitiesViewModel
import com.example.bostatask.viewmodel.UiState
import com.example.bostatask.UI.components.CityItem
import com.example.bostatask.UI.components.TopBarWithCloseButton
import com.example.bostatask.UI.components.SearchBar

@Composable
fun CityListScreen(viewModel: CitiesViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val expandedCities by viewModel.expandedCities
    var searchText by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarWithCloseButton()
            SearchBar(searchText = searchText, onSearchTextChange = { searchText = it })
            Spacer(modifier = Modifier.height(8.dp))

            when (uiState) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Error -> {
                    Text("Error: ${(uiState as UiState.Error).message}")
                }
                is UiState.Success -> {
                    val cities = (uiState as UiState.Success).cities
                        .filter {city ->
                            city.cityName.contains(searchText, ignoreCase = true) ||
                                    city.districts.any { it.districtName.contains(searchText, ignoreCase = true) } }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        items(cities) { city ->
                            CityItem(
                                city = city,
                                isExpanded = expandedCities[city.cityId] == true,
                                onExpandToggle = { viewModel.toggleCityExpansion(city.cityId) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}