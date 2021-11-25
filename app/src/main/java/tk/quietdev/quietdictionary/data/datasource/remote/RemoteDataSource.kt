package tk.quietdev.quietdictionary.data.datasource.remote

import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource

interface RemoteDataSource {
    suspend fun fetchWord(word: String) : Resource<WordModel>

}