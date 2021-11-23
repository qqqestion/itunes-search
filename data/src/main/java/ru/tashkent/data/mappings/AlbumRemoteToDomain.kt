package ru.tashkent.data.mappings

import ru.tashkent.data.api.Result
import ru.tashkent.domain.Album

class AlbumRemoteToDomain {

    fun map(remote: Result) = Album(remote.collectionName, remote.artistName)
}