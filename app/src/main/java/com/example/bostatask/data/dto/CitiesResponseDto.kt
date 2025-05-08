package com.example.bostatask.data.dto

data class CitiesResponseDto(
    val success: Boolean,
    val message: String,
    val data: List<CityDto>
)
