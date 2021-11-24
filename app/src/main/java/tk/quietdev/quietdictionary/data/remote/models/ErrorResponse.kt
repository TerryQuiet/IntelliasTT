package tk.quietdev.quietdictionary.data.remote.models

data class ErrorResponse(
    val message: String,
    val resolution: String,
    val title: String
)