package tk.quietdev.quietdictionary.data.remote.models

data class DictionaryResponse(
    val meanings: List<MeaningRemote>,
    val phonetic: String?,
    val word: String
)

data class MeaningRemote(
    val definitions: List<DefinitionRemote>,
    val partOfSpeech: String
)

data class DefinitionRemote(
    val definition: String
)

