package com.route.news.ui.screens.home.composables.News

import android.R
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.news.api.ApiManager
import com.route.news.api.model.SourceDM
import com.route.news.api.model.SourcesResponse
import com.route.news.ui.composables.DefaultErrorMessage
import com.route.news.ui.composables.DefaultLoadingView
import com.route.news.ui.model.Category
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections.list

@Composable
fun NewsTab(category: Category) {
    var tabs by remember { mutableStateOf<List<SourceDM>?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedTabIndex by remember { mutableStateOf(0) }

    ///Side Effect
    DisposableEffect(Unit) {
        isLoading = true
        ApiManager.getWebServices().getSources(category = category.title)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse?>,
                    response: Response<SourcesResponse?>
                ) {
                    isLoading = false
                    Log.e("getSources - onResponse", "code = ${response.code()}")
                    if (response.isSuccessful) {
                        tabs = response.body()!!.sources
                    } else {
                        errorMessage = response.message()
                    }
                    Log.e("getSources - onResponse", "body = ${response.body()}")
                }

                override fun onFailure(
                    call: Call<SourcesResponse?>,
                    t: Throwable
                ) {
                    isLoading = false
                    Log.e("getSources - onFailure", "code = ${t.message}")
                    errorMessage = t.message ?: "Something Went Wrong Please Try Again Later"
                }

            })
        onDispose {}
    }




    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            DefaultLoadingView()
        }
        if (!tabs.isNullOrEmpty()) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Black,
                indicator = { tabsPositions ->
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabsPositions[selectedTabIndex])
                            .height(1.dp)
                            .background(color = Color.White)
                    )
                }, divider = {}
            ) {
                for (i in 0 until (tabs?.size ?: -1)) {
                    var isSelected = selectedTabIndex == i
                    Tab(
                        selected = selectedTabIndex == 1,
                        onClick = {
                            selectedTabIndex = i
                        }, modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = tabs!![i].name ?: "",
                            style = if (isSelected) NewsTypography.bodyMedium else NewsTypography.bodySmall
                        )
                    }

                }
            }
            ArticlesList(source = tabs!![selectedTabIndex].id?:"")
        }

        if (errorMessage?.isNotEmpty() == true) {
            DefaultErrorMessage(errorMessage!!) {

            }
            }
        }
    }










