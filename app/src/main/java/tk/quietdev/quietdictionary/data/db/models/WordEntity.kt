package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = false) val word: String,
    val phonetic: String?,
)






