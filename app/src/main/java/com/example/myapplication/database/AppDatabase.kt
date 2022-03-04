package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.dao.CategoryDao
import com.example.myapplication.dao.WordDao
import com.example.myapplication.entity.Category
import com.example.myapplication.entity.Word

@Database(entities = [Category::class,Word::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun wordDao(): WordDao

    companion object {
        private var instance:AppDatabase? = null


        @Synchronized
        fun getInstance(context: Context):AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"category_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }


}