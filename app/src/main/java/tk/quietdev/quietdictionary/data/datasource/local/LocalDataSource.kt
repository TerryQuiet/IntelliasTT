package tk.quietdev.quietdictionary.data.datasource.local

import kotlinx.coroutines.flow.Flow
import tk.quietdev.core.domain.models.WordModel

interface LocalDataSource {
    suspend fun getWordWithContent(word: String) : WordModel
    suspend fun cacheWord(wordModel: WordModel)
    fun flowCachedWords(): Flow<List<String>>
}