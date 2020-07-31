package com.boonapps.gallery.api

import com.boonapps.gallery.BuildConfig
import com.boonapps.gallery.model.PixaApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApiService {

    @GET("api/")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("key") clientId: String = BuildConfig.ACCESS_KEY
    ) : PixaApiResponse

    companion object {
        private const val BASE_URL = "https://pixabay.com/"

        fun create(): PixaApiService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PixaApiService::class.java)
        }
    }
}
