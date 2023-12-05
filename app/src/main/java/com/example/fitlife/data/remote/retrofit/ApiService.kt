package com.example.fitlife.data.remote.retrofit

import com.example.fitlife.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    // REGISTER
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): CommonResponse

    // LOGIN
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    // ADD NEW STORY
    @Multipart
    @POST("stories")
    suspend fun postStory(
        @Header("Authorization") authorization: String,
        @Part photo: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Double,
        @Part("lon") lon: Double
    ): CommonResponse


    // ADD NEW STORY WITH GUEST ACC (WITHOUT AUTH)
    @Multipart
    @POST("stories/guest")
    suspend fun guestPostStory(
        @Header("Authorization") authorization: String,
        @Part("description") description: String,
        @Part photo: MultipartBody.Part,
        @Part("lat") lat: Float?,
        @Part("lon") lon: Float?
    ): Call<CommonResponse>

    // GET ALL STORIES
    @GET("stories")
    suspend fun getAllStories(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20,
        @Query("location") location: Int = 1
    ): AllStoriesResponse

    // GET DETAIL STORY
    @GET("stories/{id}")
    fun getDetailStory(
        @Header("Authorization") authorization: String,
        @Path("id") storyId: String
    ): Call<DetailStoryResponse>

    // GET ALL STORIES
    @GET("stories")
    suspend fun getAllStoriesPager(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int? = null,
        @Query("location") location: Int = 1
    ): AllStoriesResponsePager


//    @GET("list")
//    suspend fun getQuote(
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): List<QuoteResponseItem>
}