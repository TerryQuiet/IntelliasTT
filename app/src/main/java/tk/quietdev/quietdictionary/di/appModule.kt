package tk.quietdev.quietdictionary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tk.quietdev.quietdictionary.ui.details.WordDetailsViewModel
import tk.quietdev.quietdictionary.ui.search.WordSearchViewModel

val appModule = module {
    /*single { ContactsFetcher(androidApplication()) }
    single { Convertor() }
    single { Validator() }
    single { PrefsHelper(androidApplication().applicationContext) }
    single { FakeDatabase(androidApplication().applicationContext, get(), get()) }

    viewModel { ContactListViewModel(get()) }
    viewModel { AddContactViewModel(get()) }
    viewModel { ContactsSharedViewModel() }
    viewModel { ContactDetailViewModel() }
    viewModel { EditProfileViewModel(get()) }
    viewModel { SettingsViewModel() }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { AppbarSharedViewModel() }
    viewModel { SettingsSharedViewModel() }*/

    viewModel { WordDetailsViewModel() }
    viewModel { WordSearchViewModel(get()) }

}