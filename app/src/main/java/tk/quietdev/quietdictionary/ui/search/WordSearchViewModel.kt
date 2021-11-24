package tk.quietdev.quietdictionary.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.core.usecase.GetWordUseCase
import tk.quietdev.level1.common.Resource

class WordSearchViewModel(
    private val getWordUseCase: GetWordUseCase
) : ViewModel() {

    private val _currentWord = MutableLiveData<Resource<WordModel>>(Resource.Loading(null))
    val currentWord: LiveData<Resource<WordModel>> get() = _currentWord

    fun getHello(word :String) {
        viewModelScope.launch {
           _currentWord.value = getWordUseCase(word)!! // no idea why it's swearing.
        }
    }

}