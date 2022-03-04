package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.SettingsListBinding
import com.example.myapplication.databinding.WordsListBinding
import com.example.myapplication.entity.Category
import com.example.myapplication.entity.Word

class WordAdapter(var onItemClickListener: OnItemClickListener): ListAdapter<Word, WordAdapter.Vh>(MyDiffUtil()){

    inner class Vh(var wordsListBinding: WordsListBinding) : RecyclerView.ViewHolder(wordsListBinding.root) {

        fun onBind(word: Word) {
            wordsListBinding.word.text = word.word
            wordsListBinding.translate.text = word.translate

            wordsListBinding.dots.setOnClickListener {
                onItemClickListener.onItemPopClick(word,wordsListBinding.dots)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(WordsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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