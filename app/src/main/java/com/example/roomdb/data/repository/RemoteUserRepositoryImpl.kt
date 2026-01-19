package com.example.roomdb.data.repository

import com.example.roomdb.data.remote.api.RetrofitClient
import com.example.roomdb.data.remote.mapper.toRemoteUser
import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.utils.NetworkResult
import retrofit2.HttpException
import java.io.IOException

class RemoteUserRepositoryImpl : RemoteUserRepository {

    override suspend fun fetchUsers(): NetworkResult<List<RemoteUser>> {
        return try {
            val response = RetrofitClient.api.getUsers()
            val users = response.map { it.toRemoteUser() }
            NetworkResult.Success(users)
        }catch (e : IOException){
            NetworkResult.Error("No Internet Connection!!")
        }catch (e : HttpException){
            NetworkResult.Error("Server Error : ${e.code()}")
        }catch (e : Exception){
            NetworkResult.Error("Something went wrong!!")
        }
    }
}