package com.app.bricklist.data.network
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface BrickApi {
    companion object {
        operator fun invoke(url: String): BrickApi {
            val okClient = OkHttpClient.Builder()
                    .addInterceptor(UserAgentInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(url)
                    .build()
                    .create(BrickApi::class.java)

        }
    }
    //get project XML
    @GET("/MyWeb/BL/{id}.xml")
    fun getProject(@Path(value = "id") id: Int): Call<ResponseBody>

}

