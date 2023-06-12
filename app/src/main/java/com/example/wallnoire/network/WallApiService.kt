package com.example.wallnoire.network

import com.example.wallnoire.const.CLIENT_ID
import com.example.wallnoire.localData.model.TopicItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WallApiService {

    @GET("topics/")
    suspend fun getTopicsList(@Query("client_id") clientId: String = CLIENT_ID): Single<List<TopicItem>>
}