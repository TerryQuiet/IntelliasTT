package tk.quietdev.quietdictionary.data.remote.models

import tk.quietdev.core.domain.models.Meaning
import tk.quietdev.core.domain.models.WordModel

data class DictionaryResponse(
    val meanings: List<MeaningRemote>,
    val phonetic: String?,
    val word: String
)

data class MeaningRemote(
    val definitions: List<DefinitionRemote>,
    val partOfSpeech: String?
)

data class DefinitionRemote(
    val definition: String
)

fun DictionaryResponse.toDomain(): WordModel {
    return WordModel(
        phonetic = phonetic,
        word = word,
        meanings = meanings.map { meaningRemote ->
            Meaning(
                definitions = meaningRemote.definitions.map {
                    it.definition
                },
                partOfSpeech = meaningRemote.partOfSpeech ?: ""
            )
        }
    )
}