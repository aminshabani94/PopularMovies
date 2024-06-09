package com.asn.popularmovies.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.asn.popularmovies.data.model.CustomResult
import com.asn.popularmovies.data.model.configuration.Configuration
import com.asn.popularmovies.data.model.popularMovies.Movie
import com.asn.popularmovies.domain.configuration.usecase.GetConfigurationUseCase
import com.asn.popularmovies.domain.popularMovies.usecase.GetPopularMoviesUseCase
import com.asn.popularmovies.utils.AN_ERROR_OCCURRED
import com.asn.popularmovies.utils.NO_INTERNET_CONNECTION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {

    private val _popularMoviesState =
        MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val popularMoviesState = _popularMoviesState.asStateFlow()

    private val _navigationState =
        MutableStateFlow<NavigationState>(NavigationState.Loading)
    val navigationState = _navigationState.asStateFlow()

    var configuration: Configuration? = null
        private set

    init {
        getConfiguration()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    _navigationState.value = NavigationState.NavigateToPopularMovies
                    _popularMoviesState.value = it
                }
        }
    }

    fun getConfiguration() {
        _navigationState.value = NavigationState.Loading
        viewModelScope.launch {
            getConfigurationUseCase().collect { result ->
                when (result) {
                    is CustomResult.Failure -> {
                        _navigationState.value = NavigationState.NavigateToErrorScreen(
                            result.error.message ?: AN_ERROR_OCCURRED
                        )
                    }

                    CustomResult.NullOrEmpty -> {
                        _navigationState.value =
                            NavigationState.NavigateToErrorScreen(AN_ERROR_OCCURRED)
                    }

                    is CustomResult.Success -> {
                        configuration = result.data
                        getPopularMovies()
                    }

                    CustomResult.NoInternet -> {
                        _navigationState.value =
                            NavigationState.NavigateToErrorScreen(NO_INTERNET_CONNECTION)
                    }
                }
            }
        }
    }
}

sealed class NavigationState {
    data object Loading : NavigationState()
    data object NavigateToPopularMovies : NavigationState()
    data class NavigateToErrorScreen(val message: String) : NavigationState()
}