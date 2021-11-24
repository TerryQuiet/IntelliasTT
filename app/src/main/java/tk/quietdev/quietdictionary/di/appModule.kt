package tk.quietdev.quietdictionary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tk.quietdev.quietdictionary.ui.details.WordDetailsViewModel
import tk.quietdev.quietdictionary.ui.search.WordSearchViewModel

val appModule = module {

    viewModel { parameters ->
        WordDetailsViewModel(
            word = parameters.get(),
            getWordUseCase = get()
        )
    }
    viewModel { WordSearchViewModel(get(), get()) }

}