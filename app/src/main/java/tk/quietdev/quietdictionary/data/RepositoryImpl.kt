package tk.quietdev.quietdictionary.data

import tk.quietdev.core.data.Repository
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource
import tk.quietdev.quietdictionary.data.datasource.local.LocalDataSource
import tk.quietdev.quietdictionary.data.datasource.remote.RemoteDataSource
import tk.quietdev.quietdictionary.util.Constants

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val roomDataSource: LocalDataSource
) : Repository {

    override suspend fun getWord(word: String): Resource<WordModel> {
        return try {
            val roomWord = roomDataSource.getWordWithContent(word)
            Resource.Success(roomWord)
        } catch (e: Exception) {
            try {
                remoteDataSource.fetchWord(word).also {
                    it.data?.let {
                        roomDataSource.cacheWord(wordModel = it)
                    }
                }
            } catch (e: Exception) {
                Resource.Error(e.message ?: Constants.UNEXPECTED_ERROR)
            }
        }
    }

    override fun flowCachedWords() = roomDataSource.flowCachedWords()


}