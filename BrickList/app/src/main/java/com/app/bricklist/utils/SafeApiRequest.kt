package com.app.bricklist.utils

import android.util.Log
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful && response.body() != null) {
            Log.e("API", response.code().toString())
            return response.body()!!

        } else {
            Log.e("API", response.body().toString())
            throw ApiException(response.code().toString())
        }
    }

}

class ApiException(message: String) : IOException(message)