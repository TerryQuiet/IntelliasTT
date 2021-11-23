package tk.quietdev.quietdictionary.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import tk.quietdev.quietdictionary.data.remote.models.DictionaryResponse

interface DictionaryApi {

    @GET("{word}")
    suspend fun getHello(@Path("word") word: String): Response<List<DictionaryResponse>>
}