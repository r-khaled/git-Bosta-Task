package com.example.bostatask.data.remote
import com.example.bostatask.data.dto.CitiesResponseDto
import com.example.bostatask.data.dto.CityDto
import retrofit2.http.GET
import retrofit2.http.Query
interface BostaApiService {
    @GET("cities/getAllDistricts")
    suspend fun getAllDistricts(
        @Query("countryId") countryId: String = "60e4482c7cb7d4bc4849c4d5"
    ): CitiesResponseDto
}