package tk.quietdev.quietdictionary.data.datasource.local


import androidx.room.withTransaction
import kotlinx.coroutines.flow.map
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.quietdictionary.data.db.WordDb
import tk.quietdev.quietdictionary.data.db.models.*


class RoomDataSource(private val dao: WordDb) : LocalDataSource{

    override suspend fun getWordWithContent(word: String) : WordModel = dao.wordDao.getWordWithContent(word).toDomain()

    override fun flowCachedWords() = dao.wordDao.getAllCachedWords().map { it.map { it.word } }

    /* looks awful, have to map every single nested object to room entity while adding Ids
    for relations */
    override suspend fun cacheWord(wordModel: WordModel) {
        dao.withTransaction {
            dao.wordDao.insert(
                WordEntity(
                    word = wordModel.word,
                    phonetic = wordModel.phonetic
                )
            )
            wordModel.meanings.forEach { meaning ->
                MeaningEntity(
                    meanId = wordModel.word + meaning.partOfSpeech,
                    wordCreatorId = wordModel.word,
                    partOfSpeech = meaning.partOfSpeech
                ).apply {
                    dao.wordDao.insert(this)
                }
                meaning.definitions.forEachIndexed { index, s ->
                    DefinitionEntity(
                        defId = wordModel.word + meaning.partOfSpeech + index,
                        definition = s
                    ).apply {
                        dao.wordDao.insert(this)
                    }
                    MeaningDefinitionCrossRef(
                        meanId = wordModel.word + meaning.partOfSpeech,
                        defId = wordModel.word + meaning.partOfSpeech + index
                    ).apply {
                        dao.wordDao.insert(this)
                    }
                }
            }
        }
    }
}