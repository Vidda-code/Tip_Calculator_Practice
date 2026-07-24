package com.example.tipcalculatorpractice.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tipcalculatorpractice.TipViewModel
import com.example.tipcalculatorpractice.ui.screens.TipScreen

sealed class Screen(val route: String) {
    data object TipScreen : Screen("tipScreen")
}

@Composable
fun NavGraphHost(
    modifier: Modifier = Modifier,
    viewModel: TipViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.TipScreen.route,
        modifier = modifier
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
                }
            )
        }
    }
}