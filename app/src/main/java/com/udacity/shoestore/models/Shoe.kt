package com.udacity.shoestore.models

import kotlinx.serialization.Serializable

@Serializable
data class Shoe(
    var name: String = "",
    var size: Double = 0.0,
    var company: String = "",
    var description: String = "",
)