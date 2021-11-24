package tk.quietdev.quietdictionary.di

import org.koin.dsl.module
import tk.quietdev.core.data.Repository
import tk.quietdev.quietdictionary.data.RepositoryImpl
import tk.quietdev.quietdictionary.data.datasource.local.RoomDataSource
import tk.quietdev.quietdictionary.data.datasource.remote.RemoteDataSource

val dataModule = module {
    single { RoomDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { RepositoryImpl(get(), get()) as Repository }
}