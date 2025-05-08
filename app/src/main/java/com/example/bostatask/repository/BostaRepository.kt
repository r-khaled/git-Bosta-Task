package com.example.bostatask.repository
import com.example.bostatask.data.dto.CityDto
import com.example.bostatask.data.remote.BostaApiService
import com.example.bostatask.data.remote.RetrofitClient.apiService

class BostaRepository(private val apiService: BostaApiService) {

    suspend fun getCities(): Result<List<CityDto>> {
        return try {
            val response = apiService.getAllDistricts()
            val cities = response.data
            Result.success(cities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}