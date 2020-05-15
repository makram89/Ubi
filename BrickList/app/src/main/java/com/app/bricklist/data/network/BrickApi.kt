package com.app.bricklist.data.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BrickApi {

    companion object {
        operator fun invoke(url: String): BrickApi {
            val okClient = OkHttpClient.Builder()
//                .addInterceptor(UserAgentInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
                .create(BrickApi::class.java)

        }
    }

    @GET("/photos")
    suspend fun getPhotos(@Query("_limit") _limit: Int = 100): Response<List<Any>>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") id: Int): Response<Any>

    @GET("/users/{id}")
    suspend fun getUser(@Path(value = "id") id: Int): Response<Any>

}

