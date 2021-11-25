package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import tk.quietdev.quietdictionary.util.Constants

data class MeaningWithDefinition(
    @Embedded val meaning: MeaningEntity,
    @Relation(
        parentColumn = Constants.MEANING_ID,
        entityColumn = Constants.DEFINITION_ID,
        associateBy = Junction(MeaningDefinitionCrossRef::class)
    )
    val definitions: List<DefinitionEntity>
)