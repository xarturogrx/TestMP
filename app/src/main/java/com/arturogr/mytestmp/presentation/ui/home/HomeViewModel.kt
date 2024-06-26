package com.arturogr.mytestmp.presentation.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturogr.mytestmp.BuildConfig
import com.arturogr.mytestmp.domain.model.Movie
import com.arturogr.mytestmp.domain.model.MovieResponse
import com.arturogr.mytestmp.domain.model.RequestSearch
import com.arturogr.mytestmp.domain.model.Response
import com.arturogr.mytestmp.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: SearchUseCase) : ViewModel() {

    private val _onLogout = MutableLiveData<Boolean>()
    val onLogout: LiveData<Boolean> = _onLogout

    private val _searchResultViewState: MutableStateFlow<Response<MovieResponse>> =
        MutableStateFlow(Response.Loading)
    val searchResultViewState = _searchResultViewState.asStateFlow()

    private val _movies = mutableStateListOf<Movie>()
    val movies: List<Movie> = _movies

    private var currentPage = 1

    fun onChangeLogout(value: Boolean) {
        _onLogout.value = value
    }

    fun loadMoreMovies() {
        viewModelScope.launch {
            _searchResultViewState.emit(
                useCase.invoke(
                    RequestSearch(
                        BuildConfig.V_API_KEY,
                        currentPage
                    )
                )
            )
        }
    }

    fun addMovies(newMovies: List<Movie>) {
        _movies.addAll(newMovies)
        currentPage++
    }
}
