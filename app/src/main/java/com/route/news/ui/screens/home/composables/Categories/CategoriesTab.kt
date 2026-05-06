package com.route.news.ui.screens.home.composables.Categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.news.ui.model.Category
import com.route.news.ui.model.categories
import com.route.news.ui.theme.Black
import com.route.news.ui.theme.NewsTypography
import com.route.news.ui.theme.White

@Composable
fun CategoriesTab(onCategoryClick: (Category) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text(
                "Good Morning\nHere is Some News For You",
                style = NewsTypography.titleSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        itemsIndexed(categories) { index, category ->
            CategoryItem(category, index){
                onCategoryClick(category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category, index: Int,onClick:()->Unit) {
    val isEven = index % 2 == 0
    val iconModifier=Modifier
        .clip(CircleShape)
        .size(40.dp)
        .background(White)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(180.dp)
            .clickable{
                onClick()
            }
    ) {
        Image(
            painter = painterResource(category.image),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds, contentDescription = category.title
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = if (isEven) Alignment.End else Alignment.Start
        ) {
            Text(category.title, style = NewsTypography.bodyLarge)
            Card(
                shape = RoundedCornerShape(120.dp),
                colors = CardColors(
                    containerColor = White.copy(alpha = .5f),
                    contentColor = Black,
                    disabledContainerColor =White,
                    disabledContentColor = White
                )
            ) {
                if (isEven) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("View All", style = NewsTypography.bodyLarge.copy(fontSize = 24.sp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            Icons.AutoMirrored.Default.KeyboardArrowRight,
                            modifier =iconModifier,
                            tint = Black,
                            contentDescription = ""
                        )

                    }//36.35
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            modifier = iconModifier,
                            tint = Black,
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("View All", style = NewsTypography.bodyLarge.copy(fontSize = 24.sp))
                        Spacer(modifier = Modifier.width(10.dp))

                    }
                }
            }

        }
    }
}
