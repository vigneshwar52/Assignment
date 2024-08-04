package com.assignment.pokemon.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemon.API.RetrofitInstance
import com.assignment.pokemon.MODELS.Pokemon
import com.assignment.pokemon.MODELS.PokemonListResponse
import kotlinx.coroutines.launch

class PokeListViewModel :ViewModel(){
    val _pokemonList = MutableLiveData<List<Pokemon>>()
    val _list:LiveData<List<Pokemon>> = _pokemonList


    fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonList()
                _pokemonList.postValue(response.results)
            } catch (e: Exception) {
                Log.e("PokemonListViewModel", "Error fetching Pokémon list", e)
            }
        }
    }
}