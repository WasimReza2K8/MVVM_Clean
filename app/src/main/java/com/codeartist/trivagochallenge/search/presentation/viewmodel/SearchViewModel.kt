package com.codeartist.trivagochallenge.search.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.search.domain.usecase.SearchUseCase
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class SearchViewModel @ViewModelInject constructor(
    private val searchUseCase: SearchUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var defaultDispatcher: CoroutineDispatcher = IO
        @VisibleForTesting set

    private val _searchText: MutableLiveData<String> = MutableLiveData("")
    val searchResult by lazy {
        _searchText.switchMap {
            liveData(context = viewModelScope.coroutineContext + defaultDispatcher) {
                if (it.isNotEmpty()) {
                    emit(DataState.loading())
                    emit(searchUseCase.execute(it))
                } else {
                    emit(DataState.success(emptyList<CharacterModel>()))
                }
            }
        }
    }

    fun setSearchString(query: String) {
        _searchText.value = query
    }
}