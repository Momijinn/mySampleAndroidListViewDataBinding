package com.example.mysamplelistview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mysamplelistview.Adapter.BookListBindingAdapter
import com.example.mysamplelistview.databinding.FragmentMainBinding
import com.example.mysamplelistview.model.Book
import com.example.mysamplelistview.viewModel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        // listviewのadapterと紐付ける
        if (viewModel.bookItems.value != null){
            binding.bookListView.adapter = BookListBindingAdapter(inflater.context,
                viewModel.bookItems.value!!
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bookList の値を監視する
        viewModel.bookItems.observe(viewLifecycleOwner) { bookList ->
            binding.bookListView.adapter = BookListBindingAdapter(view.context, bookList)
        }

        // listviewのアイテムクリック
        binding.bookListView.setOnItemClickListener { adapter, view, position, _ ->
            val book = adapter.getItemAtPosition(position) as Book
            viewModel.selectedBook(view.context, book)
        }

        // itemの追加ボタン
        binding.bookAddButton.setOnClickListener {
            viewModel.addBook()
        }

        // itemの削除ボタン
        binding.bookRemoveButton.setOnClickListener {
            viewModel.removeBook()
        }

    }
}