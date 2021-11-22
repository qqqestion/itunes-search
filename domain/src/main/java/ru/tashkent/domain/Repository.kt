package ru.tashkent.domain

interface Repository {

    suspend fun searchAlbums(albumName: String): Either<Throwable, List<Album>>
}