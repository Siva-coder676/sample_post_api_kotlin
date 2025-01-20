package com.example.sample_post_api

data class PostApiPayload(
    val body: String,
    val title: String,
    val userId: Int
)

