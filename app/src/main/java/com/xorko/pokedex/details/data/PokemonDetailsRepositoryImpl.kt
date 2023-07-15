package com.xorko.pokedex.details.data

import com.xorko.pokedex.common.data.models.PokemonDto
import com.xorko.pokedex.common.data.remote.PokeApi
import com.xorko.pokedex.details.domain.PokemonDetailsRepository
import retrofit2.awaitResponse

class PokemonDetailsRepositoryImpl(
    private val pokeApi: PokeApi
) : PokemonDetailsRepository {
    override suspend fun getPokemon(name: String): PokemonDto? {
        val response = pokeApi.getPokemon(name).awaitResponse()

        if (response.isSuccessful) {
            // TODO: Store the pokemon into a Room table
            return response.body()
        }

        return null
    }
}