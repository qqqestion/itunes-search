package ru.tashkent.itunes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.tashkent.data.di.Module
import ru.tashkent.domain.GetAlbums

class AlbumsViewModel : ViewModel() {

    private val getAlbums =
        GetAlbums(Module.repository)

    init {
        viewModelScope.launch {
            Log.d("!!!", "Init")
            load()
        }
    }

    fun load() {
        viewModelScope.launch {
            Log.d("!!!", "Launching")
            val albums = getAlbums.invoke(GetAlbums.Params("Escape plan"))
            Log.d("!!!", "Albums: $albums")
        }
    }
}