package tk.quietdev.quietdictionary.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import tk.quietdev.quietdictionary.data.db.WordDao
import tk.quietdev.quietdictionary.data.db.WordDb


val roomModule = module {
    fun provideDataBase(application: Application): WordDb {
        return Room.databaseBuilder(application, WordDb::class.java, "WORD_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: WordDb): WordDao {
        return dataBase.wordDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }

}
