package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.entity.Category
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Query("select * from category")
    fun getAllCategory(): Flowable<List<Category>>

    @Query("select * from category")
    fun getAllKategoria(): List<Category>

    @Insert
    fun addCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category):Single<Int>

    @Update
    fun updateCategory(category: Category)
}