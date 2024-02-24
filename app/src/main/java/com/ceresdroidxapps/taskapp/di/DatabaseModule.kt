package com.ceresdroidxapps.taskapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ceresdroidxapps.taskapp.utils.Constants
import com.ceresdroidxapps.taskapp.data.db.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }



    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

}