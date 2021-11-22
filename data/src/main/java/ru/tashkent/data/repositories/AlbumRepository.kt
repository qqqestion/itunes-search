package ru.tashkent.data.repositories

import ru.tashkent.data.api.MusicService
import ru.tashkent.data.api.Result
import ru.tashkent.domain.Album
import ru.tashkent.domain.Either
import ru.tashkent.domain.Repository
import java.lang.Exception

class AlbumRepository internal constructor(
    private val musicService: MusicService
) : Repository {

    override suspend fun searchAlbums(albumName: String): Either<Throwable, List<Album>> {
        val albums = musicService.searchAlbums(albumName)
        val mapper =
            fun(apiAlbum: Result): Album = Album(apiAlbum.collectionName, apiAlbum.artistName)
        return try {
            Either.Right(albums.body()?.results?.map(mapper) ?: emptyList())
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

}