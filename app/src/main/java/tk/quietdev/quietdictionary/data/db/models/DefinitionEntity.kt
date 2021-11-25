package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = false) val defId: String,
    val definition: String
)