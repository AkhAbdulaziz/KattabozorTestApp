package com.axmedov.testapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import com.axmedov.testapp.components.ItemProduct
import com.axmedov.testapp.components.TopBar
import com.axmedov.testapp.navigation.AppScreen
import com.axmedov.testapp.utils.showMessageOnTopOfScreen

class HomeScreen : AppScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = getViewModel<HomeViewModelImpl>()
        HomeScreenContent(viewModel)
    }
}

@Composable
private fun HomeScreenContent(viewModel: HomeViewModel) {
    val productsList by viewModel.productsState.collectAsState()
    val progressState by viewModel.progressState.collectAsState()
    showMessageOnTopOfScreen(viewModel.errorState.collectAsState().value)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(text = "Products", backButtonEnabled = false)

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(180.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    productsList.offers?.let {
                        items(it) { offer ->
                            if (offer != null) {
                                ItemProduct(offer = offer, modifier = Modifier.clickable {
                                    viewModel.navigateToDetailsScreen(offer)
                                })
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(8.dp)
            )
        }
        if (progressState) {
            CircularProgressIndicator()
        }
    }
}
