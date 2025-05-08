package com.example.bostatask.data.dto

data class CityDto(
    val cityId : String,
    val cityName :String,
    val cityOtherName: String,
    val cityCode: String,
    val districts : List<DistrictDto> = emptyList(),
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean
)
