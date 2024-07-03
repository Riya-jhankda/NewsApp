package com.example.infosphere.models

import com.example.infosphere.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)