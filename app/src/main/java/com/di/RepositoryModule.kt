package com.di

import com.repository.TaskRepository
import com.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

interface RepositoryModule {
    @Binds
    fun bindsTaskRepository(
        taskRepository: TaskRepositoryImpl
    ): TaskRepository

}