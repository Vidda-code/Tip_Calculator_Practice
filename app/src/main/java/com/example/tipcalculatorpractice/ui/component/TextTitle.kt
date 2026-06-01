package com.example.tipcalculatorpractice.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(title: String) {
    Text(
        text = title,
        color = Color.Gray,
        fontSize = 18.sp
    )
}