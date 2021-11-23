package tk.quietdev.quietdictionary.data.remote.models

data class DictionaryResponse(
    val meanings: List<Meaning?>,
    val phonetic: String?,
    val word: String
)

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)

data class Definition(
    val definition: String
)