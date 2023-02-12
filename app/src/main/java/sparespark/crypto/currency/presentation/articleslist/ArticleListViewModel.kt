package sparespark.crypto.currency.presentation.articleslist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sparespark.crypto.currency.core.ResultWrapper
import sparespark.crypto.currency.core.secret.Constants
import sparespark.crypto.currency.core.secret.Constants.TAG
import sparespark.crypto.currency.domain.usecase.GetArticlesUseCase
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // states
    private val _state = mutableStateOf(ArticlesListViewState())
    var state: State<ArticlesListViewState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ART_QUERY)?.let { query ->
            getArticles(query)
        }
    }

    private fun getArticles(query: String) {
        getArticlesUseCase.invoke(query).onEach { result ->
            when (result) {
                is ResultWrapper.Loading ->
                    _state.value =
                        ArticlesListViewState(isLoading = true)
                is ResultWrapper.Error ->
                    _state.value =
                        ArticlesListViewState(error = result.message ?: Constants.ERROR_OCCURRED)
                is ResultWrapper.Success ->
                    _state.value =
                        ArticlesListViewState(articles = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}