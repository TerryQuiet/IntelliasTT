package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MeaningEntity(
    @PrimaryKey(autoGenerate = false) val meanId: String,
    val wordCreatorId: String,
    val partOfSpeech: String,
)