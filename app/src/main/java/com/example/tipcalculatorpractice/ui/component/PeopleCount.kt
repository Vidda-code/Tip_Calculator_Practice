package com.example.tipcalculatorpractice.ui.component


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PeopleCount(peopleCount: Int, tipPerPerson: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 6.dp,
                vertical = 6.dp
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = Color.LightGray
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            colors = CardDefaults.cardColors(
                Color(0xFFAFCCC5)
            ),
            shape = RoundedCornerShape(6.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Profile Logo",
                modifier = Modifier.padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Person $peopleCount",
                fontSize = 18.sp
            )
            Text(
                text = "Equal Share",
                fontSize = 12.sp
            )
        }

        Text(
            text = "₦$tipPerPerson",
            fontSize = 18.sp
        )
    }
}