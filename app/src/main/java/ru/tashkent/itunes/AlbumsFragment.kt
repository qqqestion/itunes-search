package ru.tashkent.itunes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class AlbumsFragment : Fragment(R.layout.fragment_albums) {

    private val viewModel: AlbumsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("!!!", "On view created")
        view.findViewById<TextView>(R.id.tv_hello).setOnClickListener {
            viewModel.load()
        }
    }
}