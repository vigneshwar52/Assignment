package com.assignment.pokemon.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemon.API.RetrofitInstance
import com.assignment.pokemon.MODELS.PokeDetail
import kotlinx.coroutines.launch

class PokeDetailedViewModel:ViewModel() {
    private val _pokeDetail = MutableLiveData<PokeDetail>()
    val _detailedList:LiveData<PokeDetail> = _pokeDetail

    fun getDetailedContent(id:String){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonDetail(id)
                _pokeDetail.postValue(response)
            }catch (e:Exception){
                Log.e("PokemonDetailedViewModel", "Error fetching Pokemon list", e)
            }
        }
    }
}