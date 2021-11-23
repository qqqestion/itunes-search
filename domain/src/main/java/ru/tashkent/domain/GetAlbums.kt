package ru.tashkent.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAlbums(
    private val repository: Repository
) {

    suspend operator fun invoke(params: Params): Either<Throwable, List<Album>> =
        withContext(Dispatchers.IO) { repository.searchAlbums(params.albumName) }

    data class Params(val albumName: String)
}