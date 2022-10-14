package com.dranoer.abnamro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.domain.RepoRepository
import com.dranoer.abnamro.ui.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    var repository: RepoRepository,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<ViewState<List<Repo>>>(ViewState.Loading)
    val stateFlow: StateFlow<ViewState<List<Repo>>> = _stateFlow

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            repository.result.collect {
                _stateFlow.tryEmit(it)
            }
            _isRefreshing.emit(false)
        }
    }
}