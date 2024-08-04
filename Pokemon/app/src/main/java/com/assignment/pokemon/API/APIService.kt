package com.assignment.pokemon.API

import com.assignment.pokemon.MODELS.Pokemon
import com.assignment.pokemon.MODELS.PokeDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("pokemon/")
    suspend fun getPokemonList():List<Pokemon>

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetail(@Path("id") id:String) : PokeDetail
}