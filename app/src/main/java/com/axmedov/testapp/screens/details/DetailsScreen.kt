package com.axmedov.testapp.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import com.axmedov.testapp.R
import com.axmedov.testapp.components.TopBar
import com.axmedov.testapp.data.responses.Offer
import com.axmedov.testapp.navigation.AppScreen

class DetailsScreen(val data: Offer) : AppScreen() {
    @Composable
    override fun Content() {
        val viewModel: DetailsViewModel = getViewModel<DetailsViewModelImpl>()
        DetailsScreenContent(data, viewModel)
    }
}

@Composable
private fun DetailsScreenContent(data: Offer, viewModel: DetailsViewModel) {

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(text = "Details", onBackButtonClick = { viewModel.back() })

            Card(
                elevation = CardDefaults.cardElevation(50.dp),
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 2.dp,
                        brush = Brush.sweepGradient(
                            listOf(
                                colorResource(R.color.blue),
                                colorResource(R.color.green)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.light_grey)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = data.image?.url,
                        contentScale = ContentScale.Fit,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            listOf(
                                colorResource(R.color.blue),
                                colorResource(R.color.green),
                                Color.Transparent
                            )
                        ),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .background(colorResource(id = R.color.light_grey))
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                ItemDetails(title = "Name", description = data.name ?: "")
                ItemDetails(title = "Brand", description = data.brand ?: "")
                ItemDetails(title = "Category", description = data.category ?: "")
                ItemDetails(title = "Merchant", description = data.merchant ?: "")

                if (!data.attributes.isNullOrEmpty()) {
                    for (item in data.attributes) {
                        ItemDetails(title = item?.name ?: "", description = item?.value ?: "")
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemDetails(title: String, description: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.blue),
                        colorResource(R.color.green)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.blue).copy(alpha = 0.7f))
                .padding(8.dp),
            textAlign = TextAlign.Center,
            text = "$title:",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(16f, TextUnitType.Sp)
        )

        Text(
            text = description, modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(8.dp),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(14f, TextUnitType.Sp)
        )
    }
}
