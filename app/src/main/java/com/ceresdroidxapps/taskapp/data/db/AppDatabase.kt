package com.ceresdroidxapps.taskapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ceresdroidxapps.taskapp.utils.Constants
import com.ceresdroidxapps.taskapp.utils.Converters

/*@Database(entities = [], version = Constants.DATABASE_VERSION)
@TypeConverters(Converters::class)*/
abstract class AppDatabase: RoomDatabase() {

}