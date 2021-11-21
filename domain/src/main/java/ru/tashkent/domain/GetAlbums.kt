package ru.tashkent.domain

class GetAlbums(
    private val repository: Repository
) {

    operator fun invoke(params: Params) = repository.getAlubms(params.albumeName)
}