package com.example.wallnoire.model.repository

import com.example.wallnoire.localData.model.TopicItem
import com.example.wallnoire.model.room.data.WallEntity
import com.example.wallnoire.network.WallApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface WallRepository {

    fun getTopics(): Single<List<TopicItem>>

    fun getAllWall(): Observable<List<WallEntity>>

    fun insertNewWall(wall: WallEntity): Completable

    fun wallApiService(): WallApiService
}