package com.xorko.pokedex.details.presentation

import com.xorko.pokedex.details.domain.models.Pokemon

data class CurrentPokemonState(
    val isLoading: Boolean = false,
    val error: String = "",
    val pokemon: Pokemon? = null
)
