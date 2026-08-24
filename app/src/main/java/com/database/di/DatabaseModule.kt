package com.database.di

import android.content.Context
import androidx.room.Room
import com.database.MIGRATION_1_2
import com.database.SecondWarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context : Context): SecondWarDatabase{
        return  Room.databaseBuilder(
            context,
            SecondWarDatabase::class.java,
            "todolist_db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}