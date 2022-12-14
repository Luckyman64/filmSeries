package com.example.monprofil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.monprofil.viewmodels.MainViewModel

@Composable
fun ScreenMovie(
    classes: WindowSizeClass,
    viewmodel: MainViewModel,
    idFilm: String?
) {
    val classeLargeur = classes.widthSizeClass
    val detailsFilm = viewmodel.movie.collectAsState()
    if (idFilm != null) {
        viewmodel.getFilmsDetails(idFilm)
    }
    val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(3) }
    when (classeLargeur) {
        WindowWidthSizeClass.Compact-> {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                item(span = span) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500" + detailsFilm.value.backdrop_path,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(bottom = 20.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                item(span = span) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(bottom = 20.dp)) {
                        Text(text = detailsFilm.value.title, fontSize = 20.sp, modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp),
                            fontWeight = FontWeight.Bold)

                    }
                }
                item(span = span) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.background(Color.White)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + detailsFilm.value.poster_path,
                            contentDescription = "",
                            modifier = Modifier
                                .background(Color.White)
                                .padding(10.dp)
                        )
                        Spacer(Modifier.height(10.dp))
                        Column() {
                            Text(
                                text = "Synopsis",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(10.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = detailsFilm.value.overview,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }

                    }
                }
                item(span = span) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(bottom = 10.dp)) {
                        Text(text = "Distribution", fontSize = 20.sp, modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp),
                            fontWeight = FontWeight.Bold)

                    }
                }
                items(detailsFilm.value.credits.cast) { credit ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(20.dp)
                            .background(Color.White)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + credit.profile_path,
                            contentDescription = "Affiche du film"
                        )
                        Text(text = credit.name)
                        Text(text = credit.character)
                    }
                }
            }
        }
        else -> {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                item(span = span) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500" + detailsFilm.value.backdrop_path,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .height(200.dp)
                    )
                }
                item(span = span) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(bottom = 20.dp)) {
                        Text(text = detailsFilm.value.title, fontSize = 20.sp, modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp),
                            fontWeight = FontWeight.Bold)

                    }
                }
                item(span = span) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.background(Color.White)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + detailsFilm.value.poster_path,
                            contentDescription = "",
                            modifier = Modifier
                                .background(Color.White)
                                .padding(10.dp)
                        )
                        Spacer(Modifier.height(10.dp))
                        Column() {
                            Text(
                                text = "Synopsis",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(10.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = detailsFilm.value.overview,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }

                    }
                }
                item(span = span) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(top = 30.dp, bottom = 30.dp)) {
                        Text(
                            text = "Sortie le " + detailsFilm.value.release_date,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .background(Color.White)
                                .padding(10.dp)
                        )
                    }
                }
                item(span = span) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 30.dp)
                    ) {
                        detailsFilm.value.genres.forEach { genre ->
                            Text(text= genre.name, fontSize = 14.sp, modifier = Modifier
                                .background(Color.White)
                                .padding(10.dp))
                        }
                    }
                }
                item(span = span) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(bottom = 10.dp)) {
                        Text(text = "T??tes d'affiche", fontSize = 20.sp, modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp),
                            fontWeight = FontWeight.Bold)

                    }
                }
                items(detailsFilm.value.credits.cast) { credit ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(20.dp)
                            .background(Color.White)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + credit.profile_path,
                            contentDescription = "Affiche du film"
                        )
                        Text(text = credit.name)
                        Text(text = credit.character)
                    }
                }
            }
        }
    }
}