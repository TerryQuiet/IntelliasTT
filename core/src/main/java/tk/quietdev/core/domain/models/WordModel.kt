package tk.quietdev.core.domain.models

data class WordModel(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val word: String
)

data class Meaning(
    val definitions: List<String>,
    val partOfSpeech: String
)



