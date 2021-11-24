package tk.quietdev.quietdictionary.data.datasource.local

import androidx.room.withTransaction
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.quietdictionary.data.db.WordDb
import tk.quietdev.quietdictionary.data.db.models.DefinitionEntity
import tk.quietdev.quietdictionary.data.db.models.MeaningDefinitionCrossRef
import tk.quietdev.quietdictionary.data.db.models.MeaningEntity
import tk.quietdev.quietdictionary.data.db.models.WordEntity

class RoomDataSource(private val dao: WordDb) {

    suspend fun test() {

        /* val test =  WordWithMeanings(
                word = WordEntity(
                    word = "word",
                    phonetic = "phonetic"
                ),
                meanings = listOf(
                    MeaningWithDefinition(
                        MeaningEntity(
                            meanId = "wordmean",
                            wordCreatorId = "word",
                            partOfSpeech = "wtf"
                        ),
                        definitions = listOf(
                            DefinitionEntity(
                                defId = 0,
                                definition = "WHATEWER"
                            )
                        )
                    )
                )
            )*/
    }

    fun flowCachedWords() = dao.wordDao.getAllCachedWords()


    suspend fun cacheWord(wordModel: WordModel) {
        dao.withTransaction {
            dao.wordDao.insert(
                WordEntity(
                    word = wordModel.word,
                    phonetic = wordModel.phonetic
                )
            )
            wordModel.meanings.forEach {  meaning->
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