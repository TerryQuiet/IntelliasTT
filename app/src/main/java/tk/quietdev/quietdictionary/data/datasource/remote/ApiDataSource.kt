package tk.quietdev.quietdictionary.data.datasource.remote

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource
import tk.quietdev.quietdictionary.data.remote.DictionaryApi
import tk.quietdev.quietdictionary.data.remote.models.ErrorResponse
import tk.quietdev.quietdictionary.data.remote.models.toDomain

class ApiDataSource(
    private val api: DictionaryApi
) : RemoteDataSource{
    override suspend fun fetchWord(word: String) : Resource<WordModel> {
        val response = api.getHello(word)
        return if (response.isSuccessful && response.body() != null) {
            Resource.Success(response.body()!!.first().toDomain())
        } else  {
            val errorMessage = errorResponseMapper().fromJson(response.errorBody()?.string())?.message
            Resource.Error(errorMessage ?: "Fail")
        }
    }
}

fun errorResponseMapper(): JsonAdapter<ErrorResponse> = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
    .adapter(ErrorResponse::class.java)