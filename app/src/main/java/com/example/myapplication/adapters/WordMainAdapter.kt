package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.SettingsListBinding
import com.example.myapplication.databinding.WordsListBinding
import com.example.myapplication.databinding.WordsMainListBinding
import com.example.myapplication.entity.Category
import com.example.myapplication.entity.Word

class WordMainAdapter(var onItemClickListener: OnItemClickListener): ListAdapter<Word, WordMainAdapter.Vh>(MyDiffUtil()){

    inner class Vh(var wordsMainListBinding: WordsMainListBinding) : RecyclerView.ViewHolder(wordsMainListBinding.root) {

        fun onBind(word: Word) {
            wordsMainListBinding.word.text = word.word
            wordsMainListBinding.translate.text = word.translate

            wordsMainListBinding.dots.setOnClickListener {
                onItemClickListener.onItemPopClick(word,wordsMainListBinding.dots)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(WordsMainListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyDiffUtil: DiffUtil.ItemCallback<Word>(){
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.equals(newItem)
        }

    }
    interface OnItemClickListener{
        fun onItemPopClick(word: Word,imageView: ImageView)

    }




}