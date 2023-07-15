package com.xorko.pokedex

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.xorko.pokedex.common.data.NoInternetInterceptor
import com.xorko.pokedex.common.data.remote.PokeApi
import com.xorko.pokedex.common.domain.ResourceRepository
import com.xorko.pokedex.details.data.PokemonDetailsRepositoryImpl
import com.xorko.pokedex.details.domain.GetPokemonUseCase
import com.xorko.pokedex.details.domain.PokemonDetailsRepository
import com.xorko.pokedex.details.presentation.PokemonDetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {

    private val module = module {
        single(named("appContext")) { androidContext() }
        single { ResourceRepository(get(qualifier = named("appContext"))) }
    }

    private val networkModule = module {
        single { provideMoshi() }
        single { provideOkhttpClient() }
        single<PokeApi> { providePokeApiRetrofit(get(), get()).create(PokeApi::class.java) }
    }

    private val pokemonDetailsModule = module {
        single<PokemonDetailsRepository> { PokemonDetailsRepositoryImpl(get()) }
        single { GetPokemonUseCase(get(), get()) }
        viewModelOf(::PokemonDetailsViewModel)
    }

    private fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun provideOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = Level.BASIC
            redactHeader("Authorization");
            redactHeader("Cookie");
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(NoInternetInterceptor())
            .build()
    }

    private fun providePokeApiRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().addConverterFactory(
        MoshiConverterFactory.create(moshi))
        .client(okHttpClient).baseUrl(BuildConfig.POKEAPI_URL).build()

    val appModule = module {
        includes(module, networkModule, pokemonDetailsModule)
    }
}