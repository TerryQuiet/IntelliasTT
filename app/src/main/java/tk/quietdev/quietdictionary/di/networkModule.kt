package tk.quietdev.quietdictionary.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tk.quietdev.quietdictionary.BuildConfig
import tk.quietdev.quietdictionary.data.remote.DictionaryApi

val networkModule = module {
    factory { providesMoshi() }
    factory { providesHttpLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get(), get()) }
}

fun providesMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

fun provideRetrofit(okHttpClient: OkHttpClient, mosh: Moshi): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(mosh)).build()
}

fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
    .apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
}

fun provideForecastApi(retrofit: Retrofit): DictionaryApi =
    retrofit.create(DictionaryApi::class.java)