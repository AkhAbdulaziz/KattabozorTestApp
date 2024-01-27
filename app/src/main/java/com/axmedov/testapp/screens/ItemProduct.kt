package com.axmedov.testapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axmedov.testapp.ui.theme.TestAppTheme
import com.axmedov.testapp.utils.sampleImageUrl
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProduct(imageUrl: String) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color.Red),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = imageUrl,
                contentDescription = "Image"
            )
        }
    }
}

@Preview
@Composable
private fun ItemProductPreview() {
    TestAppTheme {
        ItemProduct(sampleImageUrl)
    }
}
