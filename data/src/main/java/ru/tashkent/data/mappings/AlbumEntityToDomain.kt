package ru.tashkent.data.mappings

import ru.tashkent.data.db.AlbumEntity
import ru.tashkent.domain.Album

class AlbumEntityToDomain {

    fun map(entity: AlbumEntity) = Album(entity.name, entity.authorName)
}