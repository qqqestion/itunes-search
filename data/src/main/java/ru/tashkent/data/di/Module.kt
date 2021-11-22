package ru.tashkent.data.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tashkent.data.api.MusicService
import ru.tashkent.data.repositories.AlbumRepository
import ru.tashkent.domain.Constants.BASE_URL
import ru.tashkent.domain.Repository

object Module {

    val retrofit = run {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val musicService =
        retrofit.create(MusicService::class.java)

    val repository: Repository = AlbumRepository(musicService)
}