package com.axmedov.testapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.axmedov.testapp.R

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String = "",
    backButtonEnabled: Boolean = true,
    onBackButtonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                Brush.linearGradient(
                    listOf(
                        colorResource(R.color.blue),
                        colorResource(R.color.green)
                    )
                )
            )
    ) {
        if (backButtonEnabled) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp),
                onClick = { onBackButtonClick() }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            fontSize = TextUnit(18f, TextUnitType.Sp)
        )
    }
}
