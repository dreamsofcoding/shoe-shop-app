package com.udacity.shoestore.models

import kotlinx.serialization.Serializable

@Serializable
data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
    val images: List<String> = mutableListOf()
)