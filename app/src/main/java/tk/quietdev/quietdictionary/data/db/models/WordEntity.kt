package tk.quietdev.quietdictionary.data.db.models

import androidx.room.*


@Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = false) val word: String,
    val phonetic: String?,
)

@Entity
data class MeaningEntity(
    @PrimaryKey(autoGenerate = false) val meanId: String,
    val wordCreatorId: String,
    val partOfSpeech: String,
)

@Entity
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = false) val defId: String,
    val definition: String
)

@Entity(primaryKeys = ["meanId", "defId"])
data class MeaningDefinitionCrossRef(
    val meanId: String,
    val defId: String
)


data class MeaningWithDefinition(
    @Embedded val meaning: MeaningEntity,
    @Relation(
        parentColumn = "meanId",
        entityColumn = "defId",
        associateBy = Junction(MeaningDefinitionCrossRef::class)
    )
    val definitions: List<DefinitionEntity>
)


data class WordWithMeanings(
    @Embedded val word: WordEntity,
    @Relation(
        entity = MeaningEntity::class,
        parentColumn = "word",
        entityColumn = "wordCreatorId"
    )
    val meanings: List<MeaningWithDefinition>
)





