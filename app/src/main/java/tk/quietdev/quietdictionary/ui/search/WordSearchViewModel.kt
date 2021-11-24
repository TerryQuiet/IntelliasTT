package tk.quietdev.quietdictionary.ui.search

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.core.usecase.FlowCachedWordsUseCase
import tk.quietdev.core.usecase.GetWordUseCase
import tk.quietdev.level1.common.Resource

class WordSearchViewModel(
    private val getWordUseCase: GetWordUseCase, flowCachedWordsUseCase: FlowCachedWordsUseCase
) : ViewModel() {

    private val _currentWord = MutableLiveData<Resource<WordModel>>(Resource.Loading(null))
    val currentWord: LiveData<Resource<WordModel>> get() = _currentWord
    val cachedWords = flowCachedWordsUseCase.invoke().asLiveData()


    fun getHello(word: String) {
        viewModelScope.launch {
            _currentWord.value = getWordUseCase(word)!! // no idea why IDE is swearing.
        }
    }

    /**
     * strangely viewModel doesn't destroy itself when I navigate to another fragment
     */
    fun clear() {
        _currentWord.value = Resource.Loading(null)
    }


}