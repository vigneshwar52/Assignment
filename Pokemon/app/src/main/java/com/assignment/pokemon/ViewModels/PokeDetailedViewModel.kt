package com.assignment.pokemon.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemon.API.RetrofitInstance
import com.assignment.pokemon.MODELS.PokeDetail
import kotlinx.coroutines.launch

class PokeDetailedViewModel:ViewModel() {
    val _pokeDetail = MutableLiveData<PokeDetail>()
    val _detailedList:LiveData<PokeDetail> = _pokeDetail

    fun getDetailedContent(id:String){
        viewModelScope.launch {
            val response = RetrofitInstance.api.getPokemonDetail(id)
            _pokeDetail.postValue(response)
        }
    }
}