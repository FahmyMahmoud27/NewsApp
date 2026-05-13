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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.news.api.ApiManager
import com.route.news.api.model.SourceDM
import com.route.news.api.model.SourcesResponse
import com.route.news.ui.composables.DefaultErrorMessage
import com.route.news.ui.composables.DefaultLoadingView
import com.route.news.ui.model.Category
import com.route.news.ui.screens.home.NewsViewModel
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections.list

@Composable
fun NewsTab(category: Category) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    var viewModel = viewModel<NewsViewModel>()
    var isLoading = viewModel.isLoading.observeAsState()
    var errorMessage = viewModel.errorMessage.observeAsState()
    var tabs = viewModel.tabs.observeAsState()


    ///Side Effect
    DisposableEffect(Unit) {
        viewModel.getSources(category.title)
        onDispose {}
    }


//15    /// 34

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading.value == true) {
            DefaultLoadingView()
        }
        if (!tabs.value.isNullOrEmpty()) {
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
                for (i in 0 until (tabs.value?.size ?: -1)) {
                    var isSelected = selectedTabIndex == i
                    Tab(
                        selected = selectedTabIndex == 1,
                        onClick = {
                            selectedTabIndex = i
                        }, modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = tabs.value!![i].name ?: "",
                            style = if (isSelected) NewsTypography.bodyMedium else NewsTypography.bodySmall
                        )
                    }

                }
            }
            ArticlesList(source = tabs.value!![selectedTabIndex].id ?: "")
        }

        if (errorMessage.value?.isNotEmpty() == true) {
            DefaultErrorMessage(errorMessage.value!!) {

            }
        }
    }
}










