package com.dranoer.abnamro.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.data.remote.Resource
import com.dranoer.abnamro.domain.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    var repository: RepoRepository,
) : ViewModel() {

    init {
        getRepos()
    }

    private val _repos = MutableLiveData<List<Repo>>()
    val repos: LiveData<List<Repo>> = _repos

    fun getRepos() {
        viewModelScope.launch {
            val result = repository.fetchRepo()

            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _repos.value = result.data.filterNotNull()
                }
                is Resource.Failure -> {}
            }
        }
    }
}