package com.example.wallnoire.localData.repository

import com.example.wallnoire.di.AllModules
import com.example.wallnoire.localData.model.TopicItem
import com.example.wallnoire.model.room.WallDatabase
import com.example.wallnoire.model.room.data.WallEntity
import com.example.wallnoire.network.WallApiService
import com.example.wallnoire.model.repository.WallRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WallRepositoryImpl @Inject constructor(
    private val database: WallDatabase
) : WallRepository {

    override fun getTopics(): Single<List<TopicItem>> {
        TODO("Not yet implemented")
    }

    override fun getAllWall(): Observable<List<WallEntity>> = database.wallDao().getAllWall()

    override fun insertNewWall(wall: WallEntity): Completable {
        return database.wallDao().insert(wall)
    }

    override fun wallApiService(): WallApiService = AllModules.provideRetrofit()

}