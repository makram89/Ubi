package com.app.bricklist.ui.addproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bricklist.data.AppRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProjectViewModel(private val repository: AppRepository) : ViewModel() {

    private var xmlMutable = MutableLiveData<String>()
    val xmlLive: LiveData<String>
        get() = xmlMutable

    private var responseCode = MutableLiveData<Int>()
    val responseLiveCode: LiveData<Int>
        get() = responseCode


    fun check(id: Int) {
//        repository and wait for response and check
        var elo: String
        val vr = repository.getProject(id)
        vr.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //handle error here
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //your raw string response

                val stringResponse = response.body()?.string()
                elo = stringResponse.toString()
                xmlMutable.value = elo
                responseCode.value = response.code()

            }

        })

    }


    //TODO
    fun add(inventory: Any) {
//Inserrt through repo
//        I and IP
    }
}
