package com.xorko.pokedex.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xorko.pokedex.Greeting
import com.xorko.pokedex.details.presentation.PokemonDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailsScreen() {
    val viewModel: PokemonDetailsViewModel = koinViewModel()
    val state = viewModel.currentPokemon.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Greeting(if (state.pokemon != null) "${state.pokemon.name} (#${state.pokemon.id})" else "Android")
                Button(onClick = {
                    viewModel.getPokemonByName(
                        if (state.pokemon?.name == "ditto") "pikachu" else "ditto"
                    )
                }) {
                    Text(text = "Click me")
                }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}