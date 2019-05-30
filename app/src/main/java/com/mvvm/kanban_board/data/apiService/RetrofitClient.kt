package com.mvvm.kanban_board.data.apiService

import com.mvvm.kanban_board.helpers.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit?{
        if(retrofit == null) {
           // val interceptor = HttpLoggingInterceptor()
           // interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
               .addInterceptor(Interceptor  { chain ->
                    //add headers??
                   val original = chain.request()
                   val request = original
                       .newBuilder()
                       .header("Content-Type", "application/json")
                       .method(original.method(), original.body())
                       .build()

                   return@Interceptor chain.proceed(request)
            })
               //.addInterceptor(interceptor)
               .connectTimeout(100, TimeUnit.SECONDS)
               .readTimeout(100, TimeUnit.SECONDS)
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
