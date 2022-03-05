package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.SettingsListBinding
import com.example.myapplication.entity.Category

class CategoryAdapter(var onItemClickListener: OnItemClickListener) : ListAdapter<Category, CategoryAdapter.Vh>(MyDiffUtil()){

    inner class Vh(var settingsListBinding: SettingsListBinding) : RecyclerView.ViewHolder(settingsListBinding.root) {

        fun onBind(category: Category) {
            settingsListBinding.kategorya.text = category.cat_name

            settingsListBinding.dots.setOnClickListener {
                onItemClickListener.onItemPopClick(category,settingsListBinding.dots)
            }

            settingsListBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(category)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(SettingsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyDiffUtil: DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.equals(newItem)
        }

    }
    interface OnItemClickListener{
        fun onItemPopClick(category: Category,imageView: ImageView)
        fun onItemClick(category: Category)

    }



}