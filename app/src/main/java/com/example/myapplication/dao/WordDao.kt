package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.entity.Category
import com.example.myapplication.entity.Word
import io.reactivex.Flowable

@Dao
interface WordDao {

    @Query("select * from word")
    fun getAllWord(): Flowable<List<Word>>

    @Query("select * from word where word_kategorya=:categoryName")
    fun getWordsByCategoryName(categoryName: String): Flowable<List<Word>>

    @Query("select * from word")
    fun getAllSoz(): List<Word>

    @Insert
    fun addWord(word: Word)

    @Delete
    fun deleteWord(word: Word)

    @Update
    fun updateWord(word: Word)
}