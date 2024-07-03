 package com.example.infosphere.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import com.example.infosphere.R
import com.example.infosphere.databinding.ActivityNewsBinding
import com.example.infosphere.db.ArticleDatabase
import com.example.infosphere.repository.NewsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

 class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController//
//        binding.bottomNavigationView.setupWithNavController(
//            findNavController(R.id.newsNavHostFragment)//        )
//        binding.bottomNavigationView.setupWithNavController(navController)
        try{
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            val navController = findNavController(R.id.newsNavHostFragment)
            bottomNavigationView.setupWithNavController(navController)
        }catch (e: Exception){
            Log.e("NewsActivity", "Error Message: ${e.message}")
        }

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

    }
}