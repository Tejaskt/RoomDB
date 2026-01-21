package com.example.roomdb.data.repository

import com.example.roomdb.data.local.dao.RemoteUserDao
import com.example.roomdb.data.remote.api.UserApi
import com.example.roomdb.data.remote.mapper.toEntity
import com.example.roomdb.data.remote.mapper.toUi
import com.example.roomdb.presentation.model.RemoteUser
import com.example.roomdb.presentation.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteUserRepositoryImpl @Inject constructor(
    private val api : UserApi,
    private val dao: RemoteUserDao
) : RemoteUserRepository {

    /*
    override suspend fun fetchUsers(): NetworkResult<List<RemoteUser>> {
        return try {
            val response = RetrofitClient.api.getUsers()
            val users = response.map { it.toRemoteUser() }
            NetworkResult.Success(users)
        }catch (e : IOException){
            NetworkResult.Error("No Internet Connection! ${e.message}")
        }catch (e : HttpException){
            NetworkResult.Error("Server Error : ${e.code()}")
        }catch (e : Exception){
            NetworkResult.Error("Something went wrong! ${e.message}")
        }
    }
    */

    override fun observeUsers(): Flow<List<RemoteUser>> =
        dao.observeUsers()
            .map { entities -> entities.map { it.toUi() } }

    override suspend fun syncUsers(): NetworkResult<Unit> {
        return try {
            val remoteUsers = api.getUsers()
            dao.insertUsers(remoteUsers.map { it.toEntity()})
            NetworkResult.Success(Unit)
        }
        catch (e : IOException){
            NetworkResult.Error("NO Internet Connection!")
        }
        catch (e : HttpException){
            NetworkResult.Error("Server Error : ${e.code()}")
        }
        catch (e: Exception){
            NetworkResult.Error("Something Went Wrong!")
        }
    }
}

/* current flow
* observeUsers() -> flow from room
* syncUsers() -> API -> Room
* syncState -> Syncing / Error / Success
* */