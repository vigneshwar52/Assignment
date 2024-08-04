package com.assignment.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.assignment.pokemon.MODELS.Pokemon
import com.assignment.pokemon.ViewModels.PokeDetailedViewModel
import com.assignment.pokemon.ViewModels.PokeListViewModel
import com.assignment.pokemon.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppNavHost(navController)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun PokemonListScreen(viewModel: PokeListViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }
    val pokemonList by viewModel._list.observeAsState(emptyList())

    LazyColumn {
        items(pokemonList) { pokemon ->
            PokemonListItem(pokemon = pokemon) {
                navController.navigate("detail/${pokemon.name}")
            }
        }
    }
}

@Composable
fun PokemonListItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable(onClick = onClick)) {
        Text(text = pokemon.name, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun PokemonDetailScreen(viewModel: PokeDetailedViewModel, pokemonId: String) {
    val pokemonDetail by viewModel._detailedList.observeAsState()

    LaunchedEffect(pokemonId) {
        viewModel.getDetailedContent(pokemonId)
    }

    pokemonDetail?.let { detail ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Image(painter = rememberImagePainter(detail.sprites.front_default), contentDescription = null)
            Text(text = detail.name, style = MaterialTheme.typography.headlineLarge)
            Text(text = "Height: ${detail.height}")
            Text(text = "Weight: ${detail.weight}")
        }
    }
}
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            val viewModel: PokeListViewModel = viewModel()
            PokemonListScreen(viewModel, navController)
        }
        composable("detail/{pokemonId}") { backStackEntry ->
            val viewModel: PokeDetailedViewModel = viewModel()
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: ""
            PokemonDetailScreen(viewModel, pokemonId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemonTheme {
        Greeting("Android")
    }
}