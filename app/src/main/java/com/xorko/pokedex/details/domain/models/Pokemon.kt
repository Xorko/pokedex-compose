package com.xorko.pokedex.details.domain.models

import com.xorko.pokedex.common.data.models.PokemonDto
import com.xorko.pokedex.common.data.models.Sprites as SpritesDto


data class Pokemon(val id: Long, val name: String, val sprites: Sprites)

data class Sprites(
    val backDefault: String,
    val backFemale: String? = null,
    val backShiny: String,
    val backShinyFemale: String? = null,
    val frontDefault: String,
    val frontFemale: String? = null,
    val frontShiny: String,
    val frontShinyFemale: String? = null,
    val animated: Sprites? = null
)

fun SpritesDto.toDomainModel() = Sprites(
    backDefault,
    backFemale,
    backShiny,
    backShinyFemale,
    frontDefault,
    frontFemale,
    frontShiny,
    frontShinyFemale
)

fun PokemonDto.toDomainModel() = Pokemon(this.id, this.name, this.sprites.toDomainModel())