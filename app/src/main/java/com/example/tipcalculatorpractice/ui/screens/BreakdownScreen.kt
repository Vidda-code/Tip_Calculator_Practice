package com.example.tipcalculatorpractice.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculatorpractice.ui.component.HeaderBanner
import com.example.tipcalculatorpractice.ui.component.PeopleCount

@Composable
fun BreakdownScreen(
    bannerTitle: String,
    totalAmount: Double,
    label: String,
    tipAmountTitle: String,
    tipAmount: Double,
    billAmountTitle: String,
    billAmount: Int,
//    onAmountChange: (Int) -> Unit,
//    onPeopleCountChange: (Int) -> Unit,
//    onTipPercentChange: (Int) -> Unit,
    totalPerPerson: Double,
    tipPercent: Int,
    peopleCount: Int,
    modifier: Modifier = Modifier
) {
    val totalPerPerson = tipPercent
    Column(
        modifier = modifier
            .padding(horizontal = 14.dp, vertical = 16.dp)
            .fillMaxHeight()
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

        Spacer(modifier = modifier.padding(vertical = 12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$peopleCount People Splitting",
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "₦%.2f each".format(totalPerPerson.toFloat()),
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
        }

        if (peopleCount > 0) {
            Spacer(modifier = Modifier.height(8.dp))
            repeat(peopleCount) { index ->
                PeopleCount(
                    personIndex = index + 1,
                    amount = tipAmount
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun BreakdownScreenPreview() {
    BreakdownScreen(
        bannerTitle = "TOTAL BILL + TIP",
        totalAmount = 5000.0,
        label = "Generous",
        tipAmountTitle = "Base Bill",
        tipAmount = 560.0,
        billAmountTitle = "Tip()",
        billAmount = 1680,
//        onAmountChange = {},
//        onTipPercentChange = {},
//        onPeopleCountChange = {},
        peopleCount = 7,
        tipPercent = 15,
        totalPerPerson = 750.0
    )
}