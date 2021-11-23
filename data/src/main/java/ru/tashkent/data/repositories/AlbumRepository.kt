package ru.tashkent.data.repositories

import ru.tashkent.data.api.MusicService
import ru.tashkent.data.db.AlbumDao
import ru.tashkent.data.mappings.AlbumEntityToDomain
import ru.tashkent.data.mappings.AlbumRemoteToDomain
import ru.tashkent.data.network.NetworkHandler
import ru.tashkent.domain.Album
import ru.tashkent.domain.Either
import ru.tashkent.domain.Repository

class AlbumRepository internal constructor(
    private val musicService: MusicService,
    private val networkHandler: NetworkHandler,
    private val musicDao: AlbumDao,
    private val remoteMapper: AlbumRemoteToDomain,
    private val entityMapper: AlbumEntityToDomain
) : Repository {

    override suspend fun searchAlbums(albumName: String): Either<Throwable, List<Album>> = when {
        networkHandler.isNetworkAvailable() -> {
            val albums = musicService.searchAlbums(albumName)
            try {
                Either.Right(albums.body()?.results?.map(remoteMapper::map) ?: emptyList())
            } catch (e: Exception) {
                Either.Left(e)
            }
        }
        else -> {
            try {
                Either.Right(musicDao.getAlbums().map(entityMapper::map))
            } catch (e: Exception) {
                Either.Left(e)
            }
        }
    }
}