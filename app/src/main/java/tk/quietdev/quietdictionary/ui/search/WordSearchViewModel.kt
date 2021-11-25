package tk.quietdev.quietdictionary.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import tk.quietdev.core.usecase.FlowCachedWordsUseCase
import tk.quietdev.core.usecase.GetWordUseCase
import tk.quietdev.level1.common.Resource

class WordSearchViewModel(
    private val getWordUseCase: GetWordUseCase, flowCachedWordsUseCase: FlowCachedWordsUseCase
) : ViewModel() {

    sealed class Event {
        data class NavigateToDetails(val text: String): Event()
        data class ShowSnackBar(val text: String): Event()
    }

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()
    val cachedWords = flowCachedWordsUseCase.invoke().asLiveData()

    fun getWord(word: String) {
        viewModelScope.launch {
            getWordUseCase(word).let {
                when (it) {
                    is Resource.Success -> navigateToDetails(it.data!!.word)
                    is Resource.Error -> showSnackBar(it.message!!)
                    else -> {}
                }
            }
        }
    }

    private fun navigateToDetails(string: String) {
        viewModelScope.launch {
            eventChannel.send(Event.NavigateToDetails(string))
        }
    }

    private fun showSnackBar(string: String) {
        viewModelScope.launch {
            eventChannel.send(Event.ShowSnackBar(string))
        }
    }

}