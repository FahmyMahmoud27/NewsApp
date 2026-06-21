package com.route.news.ui.screens.home.composables.News

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.news.ui.composables.DefaultErrorMessage
import com.route.news.ui.composables.DefaultLoadingView
import com.route.news.ui.model.Category
import com.route.news.ui.screens.home.NewsViewModel
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography

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
        if (tabs.value != null) {
            if (tabs.value!!.isNotEmpty()) {
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
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("Something Went Wrong Please Try Again Later")
                }
            }


        }

        if (errorMessage.value?.isNotEmpty() == true) {
            DefaultErrorMessage(errorMessage.value!!) {

            }
        }
    }
}










