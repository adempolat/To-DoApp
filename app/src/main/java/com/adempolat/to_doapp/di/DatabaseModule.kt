package com.adempolat.to_doapp.di

import android.content.Context
import androidx.room.Room
import com.adempolat.to_doapp.data.AppDatabase
import com.adempolat.to_doapp.utilities.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabse(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,AppDatabase::class.java,DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideTodoDao(database: AppDatabase)= database.todoDao()
}