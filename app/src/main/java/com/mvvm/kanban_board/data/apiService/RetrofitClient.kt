package com.mvvm.kanban_board.data.apiService

import com.mvvm.kanban_board.session.SessionManager
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private var authRetrofit: Retrofit? = null


    fun getClient(): Retrofit? {
        if (retrofit == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val original = chain.request()
                    val request = original
                        .newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build()

                    return@Interceptor chain.proceed(request)
                })
                .addInterceptor(loggingInterceptor)
                .connectTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS)
                .build()


            retrofit = Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun getAuthorizeClient(): Retrofit? {
        if (authRetrofit == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val original = chain.request()
                    val request = original
                        .newBuilder()
                        .header("Content-Type", "application/json")
                        .addHeader("Authorization", "JWT " + SessionManager.accessToken)
                        .method(original.method(), original.body())
                        .build()

                    return@Interceptor chain.proceed(request)
                })
                .addInterceptor(loggingInterceptor)
                .connectTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS)
                // .authenticator() -> refresh token
                .build()


            retrofit = Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }



}
