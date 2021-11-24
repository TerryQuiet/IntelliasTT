package tk.quietdev.quietdictionary.di

import org.koin.dsl.module
import tk.quietdev.core.usecase.FlowCachedWordsUseCase
import tk.quietdev.core.usecase.GetWordUseCase

val useCaseModule = module {
    single { GetWordUseCase(get()) }
    single { FlowCachedWordsUseCase(get()) }
}