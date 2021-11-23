package ru.tashkent.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MusicService {

    @POST("search?media=music&entity=album&attribute=albumTerm")
    suspend fun searchAlbums(@Query("term") albumName: String): Response<Resp>
}