package com.database.di


import com.database.SecondWarDatabase
import com.database.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun providesTaskDao(database: SecondWarDatabase): TaskDao {
        return database.taskDao()
    }
}