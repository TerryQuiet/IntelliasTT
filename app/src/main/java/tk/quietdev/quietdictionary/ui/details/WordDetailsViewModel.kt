package tk.quietdev.quietdictionary.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.core.usecase.GetWordUseCase

class WordDetailsViewModel(private val getWordUseCase: GetWordUseCase , word: String) : ViewModel() {

    private val _currentWord = MutableLiveData<WordModel>()
    val currentWord: LiveData<WordModel> get() = _currentWord

    init {
        getWord(word)
    }

    private fun getWord(word :String) {
        viewModelScope.launch {
            getWordUseCase(word).data?.let {
                _currentWord.value = it
            }
        }
    }
}