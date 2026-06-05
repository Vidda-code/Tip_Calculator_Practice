package com.example.tipcalculatorpractice.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculatorpractice.ui.component.HeaderBanner

@Composable
fun TipScreen(
    bannerTitle: String,
    totalAmount: Int,
    label: String,
    tipAmountTitle: String,
    tipAmount: Int,
    billAmountTitle: String,
    billAmount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        HeaderBanner(
            title = bannerTitle,
            label = label,
            totalAmount = totalAmount,
            billAmountTitle = billAmountTitle,
            billAmount = billAmount,
            tipAmountTitle = tipAmountTitle,
            tipAmount = tipAmount
        )
    }
}