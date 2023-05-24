package com.example.optemates

data class User(
    val login : String,
    val id: Int,
    val avatar_url: String,
    val follower : Int,
    val following : Int
)
