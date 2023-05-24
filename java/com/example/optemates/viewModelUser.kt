package com.example.optemates

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.optemates.API.ApiConfig
import com.example.optemates.API.RetrofitCliet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class viewModelUser : ViewModel() {
    val listUser = MutableLiveData<ArrayList<User>>()
    fun setSearchUsers(query:String){
        RetrofitCliet.apiInstance
            .getDataUser(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    Log.d("Vm cari user", "onResponse"+response.toString())
                   if (response.isSuccessful){
                       listUser.postValue(response.body()?.items)

                       Log.d("Hasil", response.body().toString())
                   }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })
    }
    fun setDummyUsers(query: String){

        val dummyData = arrayListOf<User>(

            User("amang lundu", 12324, "https://avatars.githubusercontent.com/u/41348944?v=4",2,2),
            User("garoxx", 123444 , "https://avatars.githubusercontent.com/u/206667?v=4",2,2),
            User("esponja", 23491, "https://avatars.githubusercontent.com/u/47607249?v=4",2,2)
        )

        listUser.postValue(dummyData)

    }
    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUser
    }
}