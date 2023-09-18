package com.example.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    PokemonList()
                }
            }
        }
    }
}

@Composable
fun PokemonList() {
    LazyColumn {
    val pokemonList = MockData().getPokemonList()
    items(pokemonList.pokemons.size) { index ->
        val pokemon = pokemonList.pokemons[index]
//         5dp padding in each side. Each element will be 120dp high and occupy the whole width.
//         The image will be aligned to one extrem of the element,
//         the text will be centred vertically, next to the image with 10dp padding to
//         separate both.

        Row(
            modifier = Modifier
                .height(120.dp)
                .padding(10.dp)
                 .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

            ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(pokemon.defaultSprite)
                    .build(),
                contentDescription = pokemon.name,
                error = painterResource(R.drawable.exclamation_mark),
                modifier = Modifier.height(120.dp).background(color = Color.White)
            )

            Text(text = pokemon.name,
                    modifier = Modifier.padding(10.dp)
            )
        }
    }
}
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        PokemonList()
    }
}