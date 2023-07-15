package com.xorko.pokedex.common.data.remote

import com.xorko.pokedex.common.data.models.PokemonDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    private companion object {
        const val POKEMON = "pokemon"
    }

    @GET("$POKEMON/{name}")
    fun getPokemon(@Path("name") name: String): Call<PokemonDto>
}