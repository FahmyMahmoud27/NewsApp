package com.route.news.ui.screens.home.composables.News

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.style.Style
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.route.news.R
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography
import com.route.news.ui.theme.Transparent


data class Article(val image: Int, val title: String, val author: String, val date: String)

val articles = listOf(
    Article(
        image = R.drawable.ic_person,
        title = "Why are budget phones getting so good?",
        author = "Irene Smith",
        date = "3 hours ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "The Best Laptops for 2024",
        author = "John Doe",
        date = "5 hours ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "Android 15: Everything you need to know",
        author = "Jane Smith",
        date = "8 hours ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "The future of AI in mobile apps",
        author = "Tech Guru",
        date = "1 day ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "Top 10 travel destinations for next summer",
        author = "Wanderlust",
        date = "2 days ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "How to stay productive while working from home",
        author = "Productivity Pro",
        date = "3 days ago"
    )
)

@Composable
fun ArticlesList() {
    LazyColumn {
        items(articles) { article ->
            ArticleItem(article)
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(

            modifier = Modifier
                .background(Black)
                .padding(2.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_person),
                modifier = Modifier
                    .border(1.dp, Color.Transparent, shape = RoundedCornerShape(16.dp))
                    .fillMaxHeight(.15f),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(article.title, style = NewsTypography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(article.author, style = NewsTypography.labelLarge)
                Text(article.date, style = NewsTypography.labelLarge)
            }
        }

    }
}



