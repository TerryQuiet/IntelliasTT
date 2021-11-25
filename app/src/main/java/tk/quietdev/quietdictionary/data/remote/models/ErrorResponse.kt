package tk.quietdev.quietdictionary.data.remote.models

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

data class ErrorResponse(
    val message: String,
    val resolution: String,
    val title: String
)

