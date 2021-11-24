package tk.quietdev.quietdictionary.data.db.models


/*
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
    @PrimaryKey(autoGenerate = true) val defId: Int,
    val meaningCreatorId: String,
    val definition: String
)

data class WordWithMeanings(
    @Embedded val word: WordEntity,
    @Relation(
        parentColumn = "word",
        entityColumn = "wordCreatorId"
    )
    val meanings: List<MeaningWithDefinitions>
)


data class MeaningWithDefinitions(
    @Embedded val meaning: MeaningEntity,
    @Relation(
        parentColumn = "defId",
        entityColumn = "meaningCreatorId"
    )
    val definitions: List<DefinitionEntity>
)
*/



