package com.example.tipcalculatorpractice.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PeopleCount(peopleCount: Int, tipPerPerson: Int) {
    Box {
        Row {
            Card(

            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Logo"
                )
            }

            Column {
                Text(text = "Person $peopleCount")
                Text(text = "Equal Share")
            }

            Text(text = "\\u20A6 $tipPerPerson")
        }
    }
}

