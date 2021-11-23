package tk.quietdev.quietdictionary.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tk.quietdev.quietdictionary.data.remote.DictionaryApi

class WordSearchViewModel(
    private val api: DictionaryApi
) : ViewModel() {

    fun getHello(word :String) {
        Log.wtf("TAG", "getHello 1", )
        viewModelScope.launch {
            Log.wtf("TAG", "getHello 2", )
            val response = api.getHello(word)
            Log.wtf("TAG", "getHello: ${response.isSuccessful}", )
        }
    }

}