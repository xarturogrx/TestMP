package com.arturogr.mytestmp.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.arturogr.mytestmp.R.drawable
import com.arturogr.mytestmp.R.string
import com.arturogr.mytestmp.domain.model.DetailMovie
import com.arturogr.mytestmp.domain.model.Response
import com.arturogr.mytestmp.presentation.ui.components.MyAlertDialog
import com.arturogr.mytestmp.presentation.ui.components.MyItemMovie
import com.arturogr.mytestmp.presentation.ui.components.MyProgressUI
import com.arturogr.mytestmp.presentation.ui.components.ToolBar
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNext: (DetailMovie) -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val listState = rememberLazyListState()
    val logout: Boolean by viewModel.onLogout.observeAsState(false)
    val response = viewModel.searchResultViewState.collectAsState(initial = Response.Loading)
    val movies = viewModel.movies

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .map { visibleItemsInfo ->
                if (visibleItemsInfo.isNotEmpty()) {
                    visibleItemsInfo.last().index
                } else {
                    0
                }
            }
            .distinctUntilChanged()
            .collect { lastVisibleItemIndex ->
                val totalItemCount = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex >= totalItemCount - 1) {
                    viewModel.loadMoreMovies()
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ToolBar(title = string.app_name, onLogout = {
            viewModel.onChangeLogout(true)
        }, onBack = {}, isLogout = true)
        MyAlertDialog(logout, stringResource(id = string.logout_title),
            icon = painterResource(id = drawable.warning),
            isCancel = true, onCancel = {
                viewModel.onChangeLogout(false)
            }, onConfirm = {
                onLogout.invoke()
            })

        when (val searchResponse = response.value) {
            Response.Loading -> {
                MyProgressUI()
            }

            is Response.Success -> {
                searchResponse.data.results.let { list ->
                    viewModel.addMovies(list)
                }
            }

            is Response.Failure -> {
                MyAlertDialog(true, searchResponse.exception?.message ?: "Error",
                    icon = painterResource(id = drawable.error), onConfirm = {
                        viewModel.loadMoreMovies()
                    })
            }
        }
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = movies
            ) { movie ->
                MyItemMovie(movie, onClick = onNext)
            }
        }
    }
}
