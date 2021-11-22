package ru.tashkent.domain

class GetAlbums(
    private val repository: Repository
) {

    suspend operator fun invoke(params: Params): Either<Throwable, List<Album>> =
        repository.searchAlbums(params.albumName)

    data class Params(val albumName: String)
}