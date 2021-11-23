package ru.tashkent.itunes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.tashkent.data.di.Module
import ru.tashkent.domain.Album
import ru.tashkent.domain.GetAlbums
import ru.tashkent.domain.getErrorOrDefault
import ru.tashkent.domain.getValueOrDefault

class AlbumsViewModel(
    private val getAlbums: GetAlbums
) : ViewModel() {

    data class UiState(
        val albums: List<Album> = emptyList(),
        val error: Throwable? = null,
        val isLoading: Boolean = false
    )

    private val stateData = MutableLiveData<UiState>()
    val state: LiveData<UiState> = stateData

    fun searchAlbums(albumName: String) {
        stateData.value = UiState(isLoading = true)
        viewModelScope.launch {
            val albums = getAlbums.invoke(GetAlbums.Params(albumName))
            stateData.value = UiState(
                albums = albums.getValueOrDefault(emptyList()),
                error = albums.getErrorOrDefault(null),
                isLoading = false
            )
        }
    }
}