package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Entity
import tk.quietdev.quietdictionary.util.Constants

@Entity(primaryKeys = [Constants.MEANING_ID, Constants.DEFINITION_ID])
data class MeaningDefinitionCrossRef(
    val meanId: String,
    val defId: String
)