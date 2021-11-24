package tk.quietdev.quietdictionary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tk.quietdev.quietdictionary.di.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, networkModule, dataModule, useCaseModule, roomModule)
        }
    }
}