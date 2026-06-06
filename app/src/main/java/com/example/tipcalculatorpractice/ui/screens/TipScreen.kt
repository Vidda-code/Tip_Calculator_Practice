package com.example.tipcalculatorpractice.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculatorpractice.ui.component.HeaderBanner
import com.example.tipcalculatorpractice.ui.component.TipOptionCard

@Composable
fun TipScreen(
    bannerTitle: String,
    totalAmount: Int,
    label: String,
    tipAmountTitle: String,
    tipAmount: Int,
    billAmountTitle: String,
    billAmount: Int,
    onAmountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
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

        Spacer(modifier = Modifier.height(32.dp))

        //Total Bill Amount
        Column {
            Text(
                text = "Total Bill Amount",
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            val displayValue = if (billAmount == 0) "" else billAmount.toString()
            OutlinedTextField(
                value = displayValue,
                onValueChange = { newValue ->
                    val cleanString = newValue.filter { it.isDigit() }
                    val newAmount = cleanString.toIntOrNull() ?: 0
                    onAmountChange(newAmount)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1B4E3B)
                ),
                leadingIcon = {
                    Text(
                        text = "₦",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B4E3B),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1B4E3B),
                    unfocusedBorderColor = Color.LightGray,
                ),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column {
            Text(
                text = "Tip Percentage",
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TipOptionCard(
                    title = 10,
                    label = "Tight",
                    isSelected = true,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                TipOptionCard(
                    title = 15,
                    label = "Fair",
                    isSelected = true,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                TipOptionCard(
                    title = 20,
                    label = "Generous",
                    isSelected = true,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Number Of People",
            color = Color.DarkGray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TipScreenPreview() {
    TipScreen(
        bannerTitle = "Total Per Person",
        totalAmount = 5000,
        label = "Generous",
        tipAmountTitle = "Tip Per Person",
        tipAmount = 560,
        billAmountTitle = "Bill Split",
        billAmount = 1680,
        onAmountChange = {}
    )
}