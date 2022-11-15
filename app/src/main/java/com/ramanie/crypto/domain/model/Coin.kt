package com.ramanie.crypto.domain.model

// this is the data class that we're gonna use when trying to populate the fragments with data,
// not the one in the data-layer. This is bc we can't access the data-layer through a presentation VM(separation of concerns)
data class Coin(
    val id: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
    )
