package com.example.wallnoire.base.di

import com.example.wallnoire.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalHttpUrl = chain.request().url
        val requestBuilder = chain
            .request()
            .newBuilder()
            .url(
                originalHttpUrl
                    .newBuilder()
                    .addQueryParameter("client_id", BuildConfig.ACCESS_KEY)
                    .build()
            )
        requestBuilder.addHeader(name = "Cache-Control", value = "public, max-stale=36000")
        return chain.proceed(requestBuilder.build())
    }
}