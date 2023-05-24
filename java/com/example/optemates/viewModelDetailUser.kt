package com.example.optemates

import android.telecom.Call
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.optemates.API.RetrofitCliet
import retrofit2.Callback
import retrofit2.Response


class viewModelDetailUser : ViewModel() {
    val user = MutableLiveData<UserDetailResponse>()
    fun setUserDetail(username: String){
        Log.d("VM detail User", "ini username yang dikirim : $username")
        RetrofitCliet.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<UserDetailResponse>
           {
               override fun onResponse(
                   call: retrofit2.Call<UserDetailResponse>,
                   response: Response<UserDetailResponse>
               ) {
                   Log.d("Vm detail user", "Response Header : "+response.headers().toString())
                   Log.d("Vm detail user", "Response Body : "+response.body().toString())
                   if (response.isSuccessful){
                       Log.d("Vm detail user", "hasil ke API succes")
                       user.postValue(response.body())
                   }
               }

               override fun onFailure(call: retrofit2.Call<UserDetailResponse>, t: Throwable) {
                   Log.d("Gagal di ViewModelDetail", t.message.toString())
               }

           })
    }
    fun setDummyUserDetail(username: String){
        Log.d("VM detail User", "ini username yang dikirim : $username")
        RetrofitCliet.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<UserDetailResponse>
           {
               override fun onResponse(
                   call: retrofit2.Call<UserDetailResponse>,
                   response: Response<UserDetailResponse>
               ) {
                   Log.d("Vm detail user", "onResponse"+response.toString())
                   if (response.isSuccessful){
                       Log.d("Vm detail user", "hasil ke API succes")
                       user.postValue(response.body())
                   }
               }

               override fun onFailure(call: retrofit2.Call<UserDetailResponse>, t: Throwable) {
                   Log.d("Gagal di ViewModelDetail", t.message.toString())
               }

           })
    }
    fun getUserDetail(): LiveData<UserDetailResponse>{
        return user
    }
}