package com.example.mysamplelistview.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysamplelistview.model.Book

class MainViewModel {
    private val _bookItems = MutableLiveData<List<Book>>(listOf())
    val bookItems: LiveData<List<Book>> get() = _bookItems


    fun selectedBook(context: Context, book: Book){
        Toast.makeText(context, "${book.id} : ${book.title}", Toast.LENGTH_SHORT).show()
    }

    fun addBook(){
        val id = _bookItems.value!!.size
        val book = Book(
            id =  id,
            title = "Book$id"
        )

        val mutableBookList = _bookItems.value!!.toMutableList()
        mutableBookList.add(book)

        _bookItems.value = mutableBookList
    }

    fun removeBook(){
        if(_bookItems.value!!.isEmpty()){
            return
        }

        val mutableBookList = _bookItems.value!!.toMutableList()
        val id = mutableBookList.size - 1
        mutableBookList.removeAt(id)

        _bookItems.value = mutableBookList
    }
}