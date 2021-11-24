package tk.quietdev.core.data

import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource

interface Repository {
    //suspend fun cacheWord(word: String, lookInCache: Boolean = true): Resource<Boolean>
    suspend fun getWord(word: String): Resource<WordModel>
}