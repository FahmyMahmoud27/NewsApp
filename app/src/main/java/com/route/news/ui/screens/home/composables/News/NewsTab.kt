package com.route.news.ui.screens.home.composables.News

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.news.ui.model.Category
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography

@Composable
fun NewsTab(category: Category) {
    val tabs = listOf<String>("Tab 1", "Tab 2", "Tab 3", "Tab 4")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
            for (i in 0 until tabs.size) {
                var isSelected = selectedTabIndex == i
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = {
                        selectedTabIndex = i
                    }) {
                    Text(
                        text = tabs[i],
                        style = if (isSelected) NewsTypography.bodyMedium else NewsTypography.bodySmall
                    )
                }

            }
        }
        ArticlesList()
    }
}










