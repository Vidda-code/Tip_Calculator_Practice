package com.example.tipcalculatorpractice.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tipcalculatorpractice.TipViewModel
import com.example.tipcalculatorpractice.ui.screens.BreakdownScreen
import com.example.tipcalculatorpractice.ui.screens.TipScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object TipScreen : Screen("tipScreen", "Calculate", Icons.Default.Calculate)
    data object BreakdownScreen : Screen("breakdownScreen", "Breakdown", Icons.Default.FilterList)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraphHost(
    modifier: Modifier = Modifier,
    viewModel: TipViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsState()

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navStackBackEntry?.destination?.route

    val screens = listOf(Screen.TipScreen, Screen.BreakdownScreen)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tip Splitter",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = "Calculator",
                        tint = Color.White,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B4E3B)
                )
            )
        },

        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                screens.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        label = { Text(screen.label) },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.TipScreen.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = Screen.TipScreen.route) {
                TipScreen(
                    bannerTitle = "Total Per Person",
                    totalAmount = state.totalPerPerson,
                    label = state.label,
                    tipAmountTitle = "Tip Per Person",
                    tipAmount = state.tipPerPerson,
                    billAmountTitle = "Bill Split",
                    billAmount = state.billAmount,
                    onAmountChange = { newAmount ->
                        viewModel.onBillAmountChange(newAmount)
                    },
                    peopleCount = state.peopleCount,
                    onPeopleCountChange = { newCount ->
                        viewModel.onPeopleCountChange(newCount)
                    },
                    tipPercent = state.tipPercent,
                    onTipPercentChange = { newPercent ->
                        viewModel.onTipPercentChange(newPercent)
                    })
            }
            // Breakdown Screen
            composable(route = Screen.BreakdownScreen.route) {
                BreakdownScreen(
                    bannerTitle = "TOTAL BILL + TIP",
                    totalAmount = state.totalPerPerson,
                    label = state.label,
                    tipAmountTitle = "Base Bill",
                    tipAmount = state.tipPerPerson,
                    billAmountTitle = "Tip(${state.tipPerPerson})",
                    billAmount = state.billAmount,
                    peopleCount = state.peopleCount,
                    tipPercent = state.tipPercent
                )
            }
        }
    }
}