package com.example.wallnoire.di.modules

import android.content.Context
import com.example.depapel.BuildConfig
import com.example.wallnoire.di.BASE_URL
import com.example.wallnoire.network.WallApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Reusable
    fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Reusable
    fun provideHttpClient(context: Context): OkHttpClient {
        val cacheSize = (10 * 1024 * 1024).toLong()

        return OkHttpClient.Builder()
            .addInterceptor(
                httpLoggingInterceptor()
            )
            .readTimeout(15.toLong(), TimeUnit.SECONDS)
            .connectTimeout(15.toLong(), TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, cacheSize))
            .build()
    }

    @Provides
    @Reusable
    fun provideRetrofit(context: Context): WallApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(
            provideHttpClient(context)
        )
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WallApiService::class.java)

    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ) =
            object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
    }

}