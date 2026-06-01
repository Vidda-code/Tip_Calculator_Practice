package com.example.tipcalculatorpractice.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderBanner(
    title: String,
    label: String,
    totalAmount: Int,
    billAmountTitle: String,
    billAmount: Int,
    tipAmountTitle: String,
    tipAmount: Int
) {
    Card(
        modifier = Modifier
            .size(
                width = 240.dp,
                height = 240.dp
            ),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {

            Row {
                TextTitle(title)

                // Label
                Box(
                    Modifier
                        .clip(shape = CircleShape)
                        .background(color = Color(0xFF233943))
                        .padding(12.dp)
                ) {
                    Text(text = label)
                }
            }

            // Total Amount( Main Amount seen on the Header Banner)
            Text(
                text = "N $totalAmount",
                fontSize = 24.sp,
                color = Color(0xF5365006)
            )

            // Horizontal Divider
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            // Tip amount & bill amount
            Row {
                Column {
                    TextTitle(tipAmountTitle)
                    Text(text = tipAmount.toString())
                }
                Column {
                    TextTitle(billAmountTitle)
                    Text(text = billAmount.toString())
                }
            }
        }
    }
}