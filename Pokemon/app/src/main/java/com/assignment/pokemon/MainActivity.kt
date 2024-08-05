package com.assignment.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = "Pokemon") },
                    modifier = Modifier.statusBarsPadding()
                )
            }) {innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    AppNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val viewModel: PokeListViewModel = viewModel()
        Column {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
            Button(onClick = { viewModel.fetchPokemonList() }) {
                Text(text = "Click Me")
            }
        }
    }
}

@Composable
fun PokemonListScreen(viewModel: PokeListViewModel, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,) {
        LaunchedEffect(Unit) {
            viewModel.fetchPokemonList()
        }
        val pokemonList by viewModel._list.observeAsState(emptyList())
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(pokemonList) { pokemon ->
                    PokemonListItem(pokemon = pokemon) {
                        navController.navigate("detail/${pokemon.name}")
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonListItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    Image(
                        painter = rememberImagePainter(detail.sprites.front_default),
                        contentDescription = null
                    )
                    Text(text = detail.name, style = MaterialTheme.typography.headlineLarge)
                    Text(text = "ID : ${detail.id.toString()}")
                    Text(text = "Height: ${detail.height}")
                    Text(text = "Weight: ${detail.weight}")
                }
            }
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
//        Greeting("Android")
    }
}