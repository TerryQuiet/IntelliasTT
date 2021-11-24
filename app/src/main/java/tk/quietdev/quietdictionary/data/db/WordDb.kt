package tk.quietdev.quietdictionary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tk.quietdev.quietdictionary.data.db.models.DefinitionEntity
import tk.quietdev.quietdictionary.data.db.models.MeaningDefinitionCrossRef
import tk.quietdev.quietdictionary.data.db.models.MeaningEntity
import tk.quietdev.quietdictionary.data.db.models.WordEntity

@Database(
    entities = [WordEntity::class, MeaningEntity::class, DefinitionEntity::class, MeaningDefinitionCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class WordDb : RoomDatabase() {
    abstract val wordDao: WordDao
}