package com.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TaskEntity::class],version = 2)

@TypeConverters(LocalDateConverters::class)
abstract class SecondWarDatabase : RoomDatabase() {

    abstract fun taskDao () : TaskDao   // برای وصل کردن dao به database

   /* companion object {
        fun createDatabase(context : Context): SecondWarDatabase {
          return  Room.databaseBuilder(
                context,
                SecondWarDatabase::class.java,
                "todolist_db"
            ).build()

        }
    }*/    //   نداریم  قسمت   این  به  نیاز   DatabaseModule  قسمت  در   hilt   کردن اضافه با
}