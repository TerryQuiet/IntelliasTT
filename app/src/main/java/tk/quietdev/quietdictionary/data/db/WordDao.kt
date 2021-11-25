package tk.quietdev.quietdictionary.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tk.quietdev.quietdictionary.data.db.models.*

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meaning: MeaningEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(definition: DefinitionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crossRef: MeaningDefinitionCrossRef)

    @Transaction
    @Query("SELECT * from WordEntity WHERE word = :word COLLATE NOCASE")
    suspend fun getWordWithContent(word: String): WordWithMeanings

    @Query("SELECT * from WordEntity WHERE word = :word")
    suspend fun getWord(word: String): WordEntity

    @Query("UPDATE WordEntity SET accessAt=:time WHERE word = :word")
    suspend fun updateWordAccessTime(word: String, time: Long)

    @Query("SELECT * FROM WordEntity ORDER by accessAt DESC")
    fun getAllCachedWords(): Flow<List<WordEntity>>

}