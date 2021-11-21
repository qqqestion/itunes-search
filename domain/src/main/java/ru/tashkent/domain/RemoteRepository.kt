package ru.tashkent.domain

interface RemoteRepository {

    suspend fun searchAlbums(albumName: String): Either<Throwable, List<Album>>
}