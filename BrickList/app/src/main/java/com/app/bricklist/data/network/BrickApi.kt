package com.app.bricklist.data.network

import com.app.bricklist.data.models.Inventories
import com.app.bricklist.data.models.InventoriesParts
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BrickApi {
    companion object {
        operator fun invoke(url: String): BrickApi {
            val okClient = OkHttpClient.Builder()
                    .addInterceptor(UserAgentInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .baseUrl(url)
                    .build()
                    .create(BrickApi::class.java)

        }
    }
    //get project XML
    @GET("/MyWeb/BL/{id}.xml")
    fun getProject(@Path(value = "id") id: Int): Call<ResponseBody>

}

