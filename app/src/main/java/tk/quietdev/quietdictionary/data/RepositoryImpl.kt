package tk.quietdev.quietdictionary.data

import tk.quietdev.core.data.Repository
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource
import tk.quietdev.quietdictionary.data.datasource.remote.RemoteDataSource

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository{

    override suspend fun getWord(word: String): Resource<WordModel> {
        return remoteDataSource.fetchWord(word)
    }

}