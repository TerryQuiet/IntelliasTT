package tk.quietdev.quietdictionary.data

import kotlinx.coroutines.flow.map
import tk.quietdev.core.data.Repository
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource
import tk.quietdev.quietdictionary.data.datasource.local.RoomDataSource
import tk.quietdev.quietdictionary.data.datasource.remote.RemoteDataSource

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val roomDataSource: RoomDataSource
) : Repository {

    override suspend fun getWord(word: String): Resource<WordModel> {
        val wordRemote = remoteDataSource.fetchWord(word).also {
            it.data?.let {
                roomDataSource.cacheWord(wordModel = it)
            }
        }
        return wordRemote
    }

    override fun flowCachedWords() = roomDataSource.flowCachedWords().map { it.map { it.word } }


}