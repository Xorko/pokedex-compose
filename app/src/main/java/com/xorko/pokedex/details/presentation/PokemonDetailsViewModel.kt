package com.xorko.pokedex.details.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xorko.pokedex.R
import com.xorko.pokedex.common.data.Resource
import com.xorko.pokedex.common.domain.ResourceRepository
import com.xorko.pokedex.details.domain.GetPokemonUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PokemonDetailsViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val resourceRepository: ResourceRepository
) : ViewModel() {

    private var _currentPokemon: MutableState<CurrentPokemonState> = mutableStateOf(
        CurrentPokemonState()
    )
    val currentPokemon: State<CurrentPokemonState> = _currentPokemon

    fun getPokemonByName(name: String) = getPokemonUseCase(name).onEach { result ->
        when (result) {
            is Resource.Success -> {
                _currentPokemon.value = CurrentPokemonState(pokemon = result.data)
            }

            is Resource.Error -> {
                _currentPokemon.value =
                    CurrentPokemonState(
                        error = result.message
                            ?: resourceRepository.getString(R.string.unexpected_error)
                    )
            }

            is Resource.Loading -> {
                _currentPokemon.value = CurrentPokemonState(isLoading = true)
            }
        }
    }.launchIn(viewModelScope)
}