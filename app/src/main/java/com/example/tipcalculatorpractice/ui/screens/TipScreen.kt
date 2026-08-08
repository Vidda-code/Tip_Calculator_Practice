package com.example.tipcalculatorpractice.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    totalAmount: Double,
    label: String,
    tipAmountTitle: String,
    tipAmount: Double,
    billAmountTitle: String,
    billAmount: Int,
    onAmountChange: (Int) -> Unit,
    onPeopleCountChange: (Int) -> Unit,
    onTipPercentChange: (Int) -> Unit,
    tipPercent: Int,
    peopleCount: Int,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(horizontal = 14.dp, vertical = 16.dp)
            .fillMaxHeight()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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

        // Tip Percentage Section
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
                    label = "Standard",
                    isSelected = tipPercent == 10,
                    onClick = {
                        onTipPercentChange(10)
                    },
                    modifier = Modifier.weight(1f)
                )
                TipOptionCard(
                    title = 15,
                    label = "Great",
                    isSelected = tipPercent == 15,
                    onClick = {
                        onTipPercentChange(15)
                    },
                    modifier = Modifier.weight(1f)
                )
                TipOptionCard(
                    title = 20,
                    label = "Generous",
                    isSelected = tipPercent == 20,
                    onClick = {
                        onTipPercentChange(20)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        //Number of people scale
        Column {
            Text(
                text = "Number Of People",
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Minus Button
                IconButton(onClick = {
                    if (peopleCount > 1) {
                        onPeopleCountChange(peopleCount - 1)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Remove,
                        contentDescription = "Decrease people",
                        tint = Color(0xFF1B4E3B),
                        modifier = Modifier.size(32.dp)
                    )
                }

                Text(
                    text = peopleCount.toString(),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )

                // Plus Button
                IconButton(onClick = {
                    onPeopleCountChange(peopleCount + 1)
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Increase people",
                        tint = Color(0xFF1B4E3B),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TipScreenPreview() {
    TipScreen(
        bannerTitle = "Total Per Person",
        totalAmount = 5000.0,
        label = "Generous",
        tipAmountTitle = "Tip Per Person",
        tipAmount = 560.0,
        billAmountTitle = "Bill Split",
        billAmount = 1680,
        onAmountChange = {},
        onTipPercentChange = {},
        onPeopleCountChange = {},
        peopleCount = 0,
        tipPercent = 15
    )
}