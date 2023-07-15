package com.xorko.pokedex.details.domain

import com.xorko.pokedex.R
import com.xorko.pokedex.common.data.Resource
import com.xorko.pokedex.common.domain.ResourceRepository
import com.xorko.pokedex.details.domain.models.Pokemon
import com.xorko.pokedex.details.domain.models.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetPokemonUseCase(
    private val pokemonDetailsRepository: PokemonDetailsRepository,
    private val resourceRepository: ResourceRepository
) {

    operator fun invoke(name: String): Flow<Resource<Pokemon>> = flow {
        try {
            emit(Resource.Loading())
            pokemonDetailsRepository.getPokemon(name)?.let {
                emit(Resource.Success(it.toDomainModel()))
            } ?: emit(
                Resource.Error(
                    resourceRepository.getString(
                        R.string.pokeapi_pokemon_not_found,
                        name
                    )
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: resourceRepository.getString(R.string.unexpected_error)
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: resourceRepository.getString(R.string.internet_error)
                )
            )
        }
    }
}