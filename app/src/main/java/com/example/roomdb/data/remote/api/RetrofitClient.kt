package com.example.roomdb.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    /* logging interceptor
    *  it logs http traffic into logcat
    *  can read and modify the response and request.
    * */
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
        /* level can be
        *  NONE - Nothing
        *  BASIC - Request + Response line
        *  BODY - Basic + headers
        *  HEADERS - headers + full body
        * */
    }

    /*  OkHttpClient
    * request execution engine
    * it handles connection, timeout, retries, interceptor, headers
    * */
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    /* Retrofit api
    * Create Request description
    * use by lazy because : Created only when first used
    * */
    val api : UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}

/* how this flow works
*
ViewModel calls repository
↓
Repository calls RetrofitClient.api.getUsers()
↓
Retrofit builds request
↓
OkHttp executes request
↓
Interceptor logs request/response
↓
Gson parses JSON
↓
Result returned to coroutine
*
* */