package com.xorko.pokedex.common.data.models

import com.squareup.moshi.Json

// Generated with QuickType, class names may not be correct
data class PokemonDto (
    val abilities: List<Ability>,
    @Json(name = "base_experience")
    val baseExperience: Long,
    val forms: List<Species>,
    @Json(name = "game_indices")
    val gameIndices: List<GameIndex>,
    val height: Long,
    @Json(name = "held_items")
    val heldItems: List<HeldItem>,
    val id: Long,
    @Json(name = "is_default")
    val isDefault: Boolean,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Long,
    @Json(name = "past_types")
    val pastTypes: List<Any?>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long
)

data class Ability (
    val ability: Species,
    @Json(name = "is_hidden")
    val isHidden: Boolean,
    val slot: Long
)

data class Species (
    val name: String,
    val url: String
)

data class GameIndex (
    @Json(name = "game_index")
    val gameIndex: Long,
    val version: Species
)

data class HeldItem (
    val item: Species,
    @Json(name = "version_details")
    val versionDetails: List<VersionDetail>
)

data class VersionDetail (
    val rarity: Long,
    val version: Species
)

data class Move (
    val move: Species,
    @Json(name = "version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)

data class VersionGroupDetail (
    @Json(name = "level_learned_at")
    val levelLearnedAt: Long,
    @Json(name = "move_learn_method")
    val moveLearnMethod: Species,
    @Json(name = "version_group")
    val versionGroup: Species
)

data class Sprites (
    @Json(name = "back_default")
    val backDefault: String,
    @Json(name = "back_female")
    val backFemale: String? = null,
    @Json(name = "back_shiny")
    val backShiny: String,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String? = null,
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "front_female")
    val frontFemale: String? = null,
    @Json(name = "front_shiny")
    val frontShiny: String,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String? = null,
    val animated: Sprites? = null
)

data class Stat (
    @Json(name = "base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: Species
)

data class Type (
    val slot: Long,
    val type: Species
)
