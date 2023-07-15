package com.xorko.pokedex.details.domain

import com.xorko.pokedex.common.data.models.PokemonDto

interface PokemonDetailsRepository {

    suspend fun getPokemon(name: String): PokemonDto?
}