package com.route.news.ui.screens.home.composables.News

import android.content.ClipData
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.news.R
import com.route.news.api.ApiManager
import com.route.news.api.model.ArticleDM
import com.route.news.api.model.ArticlesResponse
import com.route.news.ui.composables.DefaultErrorMessage
import com.route.news.ui.composables.DefaultLoadingView
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography
import com.route.news.ui.theme.Transparent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun ArticlesList(source: String) {
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var articles by remember { mutableStateOf<List<ArticleDM>?>(null) }

    DisposableEffect(source) {
        isLoading = true
        errorMessage = null

        ApiManager.getWebServices().getArticles(source = source)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse?>,
                    response: Response<ArticlesResponse?>
                ) {
                    isLoading = false
                    if (response.isSuccessful) {
                        articles = response.body()!!.articles
                    } else {
                        errorMessage = response.message()
                    }
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>,
                    t: Throwable
                ) {
                    isLoading = false
                    errorMessage = t.message ?: "Something Went Wrong Please Try Again Later"

                }

            })

        onDispose { }
    }




    LazyColumn {
        if (isLoading) {
            item {
                DefaultLoadingView()
            }
        }
        if (errorMessage?.isNotEmpty() == true) {
            item {
                DefaultErrorMessage(errorMessage!!) {

                }
            }

        }
        if (!articles.isNullOrEmpty()) {
            items(articles!!) { article ->
                ArticleItem(article)
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



