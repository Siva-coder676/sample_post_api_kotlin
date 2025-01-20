package com.example.sample_post_api
import PostApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("posts")
    fun createPost(@Body postRequest: PostApiPayload): Call<PostApiResponse>
}