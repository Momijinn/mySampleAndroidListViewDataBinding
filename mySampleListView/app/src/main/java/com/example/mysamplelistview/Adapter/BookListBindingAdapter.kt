package com.example.mysamplelistview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.mysamplelistview.databinding.BookItemBinding
import com.example.mysamplelistview.model.Book

class BookListBindingAdapter(_context: Context, _bookList: List<Book>):BaseAdapter() {
    private var inflater: LayoutInflater = LayoutInflater.from(_context)
    private var bookList = _bookList


    override fun getCount(): Int {
        return bookList.size
    }

    override fun getItem(index: Int): Any {
        return bookList[index]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: BookItemBinding
        if(convertView == null){
            binding = BookItemBinding.inflate(inflater, parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as BookItemBinding
        }
        binding.book = getItem(position) as Book

        return binding.root
    }
}