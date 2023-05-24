package com.example.optemates.API

import com.example.optemates.User
import com.example.optemates.UserDetailResponse
import com.example.optemates.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
//    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDataUser(
        @Query("q") query: String
//        @Path("username") username: String
    ): Call<UserResponse>



    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<UserDetailResponse>



    @GET("user/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>


    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}