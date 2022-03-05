package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.entity.Word
import io.reactivex.Flowable

@Dao
interface WordDao {

    @Query("select * from word")
    fun getAllWord(): Flowable<List<Word>>

    @Query("select * from word where word_kategorya=:categoryName")
    fun getWordsByCategoryName(categoryName: String): Flowable<List<Word>>

    @Query("select * from word where word_kategorya=:categoryId")
    fun getWordsByCategoryId(categoryId: Int): Flowable<List<Word>>

    @Query("select * from word where tanlangan=:Selected")
    fun getWordsBySelection(Selected: String): Flowable<List<Word>>

    @Query("select * from word where word_kategorya=:categoryId")
    fun getWordsByCategoryIdd(categoryId: Int): List<Word>

    @Query("select * from word")
    fun getAllSoz(): List<Word>

    @Insert
    fun addWord(word: Word)

    @Delete
    fun deleteWord(word: Word)

    @Query("delete from word where word_kategorya=:categoryId")
    fun deleteByCategoryIdd(categoryId: Int)

//    @TypeConverters(WordDao::class)
//    @Query("delete from word where word_kategorya in (:idList)")
//    fun deleteDataById(idList: ArrayList<Word>)

    @Update
    fun updateWord(word: Word)
}