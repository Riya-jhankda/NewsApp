package com.example.infosphere.repository

import com.example.infosphere.api.RetrofitInstance
import com.example.infosphere.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}