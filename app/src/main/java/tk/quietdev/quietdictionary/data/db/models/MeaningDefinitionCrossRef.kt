package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Entity

@Entity(primaryKeys = ["meanId", "defId"])
data class MeaningDefinitionCrossRef(
    val meanId: String,
    val defId: String
)