package tk.quietdev.quietdictionary.util

import tk.quietdev.core.domain.models.Meaning
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.quietdictionary.data.remote.models.DictionaryResponse

fun DictionaryResponse.toDomain(): WordModel {
        return WordModel(
            phonetic = phonetic,
            word = word,
            meanings = meanings.map { meaningRemote ->
                Meaning(
                    definitions = meaningRemote.definitions.map {
                        it.definition
                    },
                    partOfSpeech = meaningRemote.partOfSpeech
                )
            }
        )
}