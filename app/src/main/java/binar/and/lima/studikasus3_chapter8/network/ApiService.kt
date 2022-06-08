package binar.and.lima.studikasus3_chapter8.network

import binar.and.lima.studikasus3_chapter8.model.GetAllNewsResponseItem
import binar.and.lima.studikasus3_chapter8.model.GetAllUserItem
import binar.and.lima.studikasus3_chapter8.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("news")
    fun getAllNews(): Call<List<GetAllNewsResponseItem>>

    @POST("user")
    fun postDataUser(@Body reqUser: ResponseRegister) : Call<GetAllUserItem>

    @GET("user")
    fun allUser(): Call<List<GetAllUserItem>>
}