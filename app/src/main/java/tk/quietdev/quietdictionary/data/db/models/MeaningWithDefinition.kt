package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MeaningWithDefinition(
    @Embedded val meaning: MeaningEntity,
    @Relation(
        parentColumn = "meanId",
        entityColumn = "defId",
        associateBy = Junction(MeaningDefinitionCrossRef::class)
    )
    val definitions: List<DefinitionEntity>
)