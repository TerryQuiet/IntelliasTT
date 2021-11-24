package tk.quietdev.quietdictionary.data.datasource.remote

import android.util.Log
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource
import tk.quietdev.quietdictionary.data.remote.DictionaryApi
import tk.quietdev.quietdictionary.util.toDomain

class RemoteDataSource(
    private val api: DictionaryApi
) {
    suspend fun fetchWord(word: String) : Resource<WordModel> {
        val response = api.getHello(word)
        return if (response.isSuccessful && response.body() != null) {
            Resource.Success(response.body()!!.first().toDomain())
        } else  {
            Log.wtf("TAG", "fetchWord MESSAGE?: ${response.message()}", ) // todo error body parser
            Resource.Error(response.message())
        }
    }
}