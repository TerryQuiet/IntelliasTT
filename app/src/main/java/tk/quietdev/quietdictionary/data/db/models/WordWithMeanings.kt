package tk.quietdev.quietdictionary.data.db.models

import androidx.room.Embedded
import androidx.room.Relation
import tk.quietdev.core.domain.models.Meaning
import tk.quietdev.core.domain.models.WordModel

data class WordWithMeanings(
    @Embedded val word: WordEntity,
    @Relation(
        entity = MeaningEntity::class,
        parentColumn = "word",
        entityColumn = "wordCreatorId"
    )
    val meanings: List<MeaningWithDefinition>
)

fun WordWithMeanings.toDomain(): WordModel {
    return WordModel(
        word = this.word.word,
        phonetic = this.word.phonetic,
        meanings = this.meanings.map { meaningWithDefinition ->
                Meaning(
                    partOfSpeech = meaningWithDefinition.meaning.partOfSpeech,
                    definitions = meaningWithDefinition.definitions.map {
                        it.definition
                    }
                )
        }
    )
}
