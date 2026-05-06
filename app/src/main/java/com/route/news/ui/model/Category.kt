package com.route.news.ui.model

import com.route.news.R

data class Category(
    val title: String,val image: Int
)




val categories = listOf<Category>(
    Category(image = R.drawable.general_dark, title = "General"),
    Category(image = R.drawable.business_dark, title = "Business"),
    Category(image = R.drawable.sport_dark, title = "Sports"),
    Category(image = R.drawable.technology_dark, title = "Technology"),
    Category(image = R.drawable.science_dark, title = "Science"),
    Category(image = R.drawable.health_dark, title = "Health"),
    Category(image = R.drawable.entertainment_dark, title = "Entertainment"),
    )