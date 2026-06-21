package com.route.news.ui.screens.home.composables.News

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.news.data.api.model.ArticleDM
import com.route.news.ui.composables.DefaultErrorMessage
import com.route.news.ui.composables.DefaultLoadingView
import com.route.news.ui.screens.home.NewsViewModel
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography


@Composable
fun ArticlesList(source: String) {

    val viewModel = viewModel<NewsViewModel>()
    val isLoading = viewModel.isLoadingArticles.observeAsState()
    val errorMessage = viewModel.articlesErrorMessage.observeAsState()
    val articles = viewModel.articles.observeAsState()

    DisposableEffect(source) {
        viewModel.getArticles(source)
        onDispose { }
    }




    LazyColumn {
        if (isLoading.value!!) {
            item {
                DefaultLoadingView()
            }
        }
        if (errorMessage.value?.isNotEmpty() == true) {
            item {
                DefaultErrorMessage(errorMessage.value!!) {

                }
            }

        }
        if (articles.value != null) {
            if (articles.value!!.isNotEmpty()) {
                items(articles.value!!) { article ->
                    ArticleItem(article)
                }
            } else {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text("No articles in this source")
                    }
                }
            }


        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(article: ArticleDM) {
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
            GlideImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))

            )
//            AsyncImage(
//                model = article.urlToImage,
//                modifier = Modifier
//                    .border(
//                        1.dp, Color.Transparent,
//                        shape = RoundedCornerShape(16.dp)
//                    )
//                    .fillMaxHeight(.15f),
//                contentDescription = ""
//            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(article.title ?: "", style = NewsTypography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(article.author ?: "", style = NewsTypography.labelLarge)
                Text(article.publishedAt ?: "", style = NewsTypography.labelLarge)
            }
        }

    }
}



