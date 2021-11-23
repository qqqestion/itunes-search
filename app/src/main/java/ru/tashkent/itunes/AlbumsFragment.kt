package ru.tashkent.itunes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class AlbumsFragment : Fragment(R.layout.fragment_albums) {

    private val viewModel: AlbumsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSearch = view.findViewById<TextView>(R.id.btn_search)
        val etInput = view.findViewById<TextView>(R.id.et_input)
        btnSearch.setOnClickListener {
            viewModel.searchAlbums(
                etInput.text.toString()
            )
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            btnSearch.isEnabled = state.isLoading.not()
        }
    }
}