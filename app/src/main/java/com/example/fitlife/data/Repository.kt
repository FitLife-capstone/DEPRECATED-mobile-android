package com.example.fitlife.data

import androidx.lifecycle.liveData
import com.example.fitlife.data.pref.UserModel
import com.example.fitlife.data.pref.UserPreference
import com.example.fitlife.data.remote.response.CommonResponse
import com.example.fitlife.data.remote.response.LoginResponse
import com.example.fitlife.data.remote.retrofit.ApiConfig
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class Repository private constructor(
    private val userPreference: UserPreference,
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun login(email: String, password: String) = liveData {
        emit(Result.Loading)
        try {
            val successResponse = ApiConfig.getApiService().login(email = email, password = password)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(Result.Error(errorResponse.message.toString()))
        }
    }

    fun register(name: String, email: String, password: String) = liveData {
        emit(Result.Loading)
        try {
            val successResponse = ApiConfig.getApiService().register(name = name, email = email, password = password)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, CommonResponse::class.java)
            emit(Result.Error(errorResponse.message))
        }
    }

//    fun getAllStories(token: String) = liveData  {
//        emit(Result.Loading)
//        try {
//            val successResponse = ApiConfig.getApiService().getAllStories(authorization = "Bearer $token",null, null)
//            emit(Result.Success(successResponse))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorResponse = Gson().fromJson(errorBody, AllStoriesResponse::class.java)
//            emit(Result.Error(errorResponse.message.toString()))
//        }
//    }
//
//    fun postStory(token: String,imageFile: File, description: String) = liveData {
//        emit(Result.Loading)
//        val requestBody = description.toRequestBody("text/plain".toMediaType())
//        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
//        val multipartBody = MultipartBody.Part.createFormData(
//            "photo",
//            imageFile.name,
//            requestImageFile
//        )
//        try {
//            val successResponse = ApiConfig.getApiService().postStory(authorization = "Bearer ${token}", photo = multipartBody, description = requestBody, null, null)
//            emit(Result.Success(successResponse))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorResponse = Gson().fromJson(errorBody, CommonResponse::class.java)
//            emit(Result.Error(errorResponse.message))
//        }
//
//    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: UserPreference,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(userPreference)
            }.also { instance = it }
    }
}