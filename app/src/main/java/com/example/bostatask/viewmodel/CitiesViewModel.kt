package com.example.bostatask.viewmodel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.bostatask.data.dto.CityDto
import com.example.bostatask.repository.BostaRepository
// UI States
sealed class UiState {
    object Loading : UiState()
    data class Success(val cities: List<CityDto>) : UiState()
    data class Error(val message: String) : UiState()
}

class CitiesViewModel(private val repository: BostaRepository) : ViewModel() {

    // Main state for API result
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    // UI state to manage expanded cities
    private val _expandedCities = mutableStateOf<Map<String, Boolean>>(emptyMap())
    val expandedCities: State<Map<String, Boolean>> = _expandedCities

    init {
        fetchCities()
    }

    private fun fetchCities() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = repository.getCities()
            _uiState.value = result.fold(
             //   onSuccess = { cities -> UiState.Success(cities) },
             //   onFailure = { exception -> UiState.Error(exception.message ?: "Unknown error") }
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message ?: "Unknown error") }
            )
        }
    }
    fun toggleCityExpansion(cityId: String) {
        _expandedCities.value = _expandedCities.value.toMutableMap().also {
            it[cityId] = !(it[cityId] ?: false)
        }
    }
}