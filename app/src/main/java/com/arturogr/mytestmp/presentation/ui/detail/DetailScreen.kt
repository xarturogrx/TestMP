package com.arturogr.mytestmp.presentation.ui.detail

import com.arturogr.mytestmp.BuildConfig
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.arturogr.mytestmp.R.string
import com.arturogr.mytestmp.core.Constants
import com.arturogr.mytestmp.domain.model.DetailMovie
import com.arturogr.mytestmp.presentation.ui.components.MyContainerUI
import com.arturogr.mytestmp.presentation.ui.components.MySpacerUI
import com.arturogr.mytestmp.presentation.ui.components.MyTextUI

@Composable
fun DetailScreen(movie: DetailMovie?, onBack: () -> Unit = {}) {
    MyContainerUI(titleTollBar = string.detail_title, isToolbar = true, upAvailable = true, onBack = onBack) {
        movie?.let {
            Column {
                Box {
                    AsyncImage(
                        model = Builder(LocalContext.current)
                            .data(BuildConfig.URL_IMAGE + movie.image)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop
                    )
                    AsyncImage(
                        model = Builder(LocalContext.current)
                            .data(BuildConfig.URL_IMAGE + movie.image)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentDescription = "Image",
                        contentScale = ContentScale.Inside
                    )
                }
                MySpacerUI()
                MyTextUI(text = movie.name, textSize = 20.sp)
                MySpacerUI()
                MyTextUI(text = stringResource(id = string.vote_count) + movie.countVotes.toString())
                MySpacerUI()
                MyTextUI(text = stringResource(id = string.vote_average) + movie.votes.toString())
                MySpacerUI()
                MyTextUI(text = stringResource(id = string.date) + movie.date)
                MySpacerUI()
                MyTextUI(text = stringResource(id = string.gender) + movie.gender)
                MySpacerUI()
                MyTextUI(text = stringResource(id = string.classification) + movie.classification)
                MySpacerUI(Constants.MARGIN_MEDIUM)
                MyTextUI(text = stringResource(id = string.detail) + movie.detail, textSize = 18.sp)
            }
        }
    }
}