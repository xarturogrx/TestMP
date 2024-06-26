package com.arturogr.mytestmp.presentation.ui.components

import com.arturogr.mytestmp.BuildConfig
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.arturogr.mytestmp.R.string
import com.arturogr.mytestmp.domain.model.DetailMovie
import com.arturogr.mytestmp.domain.model.Movie

@Composable
fun MyItemMovie(movie: Movie, onClick: (DetailMovie) -> Unit = {}) {
    Card(modifier = Modifier
        .clickable {
            onClick(
                DetailMovie(
                    name = movie.title,
                    image = movie.poster_path,
                    countVotes = movie.vote_count,
                    votes = movie.vote_average,
                    detail = movie.overview,
                    date = movie.release_date,
                    gender = movie.genre_ids.toString(),
                    classification = movie.adult,
                )
            )
        }
        .padding(5.dp)
        .semantics { contentDescription = "Item Result" },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = Builder(LocalContext.current)
                    .data(BuildConfig.URL_IMAGE + movie.poster_path)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, start = 5.dp)
                ) {
                    MyTextUI(text = movie.title, textSize = 20.sp)
                    MySpacerUI()
                    MyTextUI(text = stringResource(id = string.vote_count) + movie.vote_count.toString())
                    MyTextUI(text = stringResource(id = string.vote_average) + movie.vote_average.toString())
                }
            }
        }
    }
}