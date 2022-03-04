package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.myapplication.databinding.CategoryItemBinding
import com.example.myapplication.entity.Category

class Category_Spinner(var list: List<Category>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Category {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mentorViewHolder: MentorViewHolder
        if (convertView == null) {
            val binding =
                CategoryItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            mentorViewHolder = MentorViewHolder(binding)
        } else {
            mentorViewHolder = MentorViewHolder(CategoryItemBinding.bind(convertView))
        }
        mentorViewHolder.mentorItemBinding.categoy.text = list[position].cat_name
        return mentorViewHolder.itemView
    }

    inner class MentorViewHolder {
        val itemView: View
        var mentorItemBinding: CategoryItemBinding


        constructor(mentorItemBinding: CategoryItemBinding) {
            itemView = mentorItemBinding.root
            this.mentorItemBinding = mentorItemBinding
        }
    }
}