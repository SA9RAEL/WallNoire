package com.example.wallnoire.domain.usecase

import com.example.wallnoire.domain.repository.LocalRepository
import com.example.wallnoire.domain.repository.RemoteRepository
import com.example.wallnoire.view.categoryview.Category
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CategoryUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    fun getCategories(): ArrayList<Category> = localRepository.getCategories()
    
}