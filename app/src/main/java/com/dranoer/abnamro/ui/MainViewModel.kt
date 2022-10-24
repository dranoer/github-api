package com.dranoer.abnamro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.data.model.RepoEntity
import com.dranoer.abnamro.domain.RepoRepository
import com.dranoer.abnamro.ui.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var repository: RepoRepository,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<ViewState<List<Repo>>>(ViewState.Loading)
    val stateFlow: StateFlow<ViewState<List<Repo>>> = _stateFlow

    private val _detailFlow = MutableStateFlow(RepoEntity())
    val detailFlow: StateFlow<RepoEntity> = _detailFlow

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

    fun getDetail(id: Long) {
        viewModelScope.launch {
            val result = repository.getDetail(id)
            _detailFlow.emit(result)
        }
    }
}