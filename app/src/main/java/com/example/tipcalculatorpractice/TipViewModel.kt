package com.example.tipcalculatorpractice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TipState(
    val billAmount: Int = 0,
    val tipPercent: Int = 15, // Default tip percentage should be fair at 15%
    val peopleCount: Int = 1, // People count should start from 1 and not zero
) {
    val tipPerPerson: Double
        get() = if (peopleCount > 0) {
            (billAmount.toDouble() * tipPercent / 100.0) / peopleCount
        } else {
            0.0
        }

    val totalPerPerson: Double
        get() = if (peopleCount > 0) {
            (billAmount.toDouble() / peopleCount) + tipPerPerson
        } else {
            0.0
        }

    val totalTipAmount: Double
        get() = billAmount * tipPercent / 100.0

    val totalAmount: Double
        get() = billAmount + totalTipAmount

    val label: String
        get() = when {
            tipPercent >= 20 -> "Generous"
            tipPercent >= 15 -> "Great"
            tipPercent >= 10 -> "Standard"
            else -> "Give Tip"
        }

    val breakdown: List<String>
        get() = if (peopleCount > 0) {
            // Force decimal math so we don't lose the kobo/cents
            val exactTotalTip = billAmount * (tipPercent / 100.0)
            val exactGrandTotal = billAmount + exactTotalTip
            val exactPerPerson = exactGrandTotal / peopleCount

            // Map creates a clean list of rows for the UI
            (1..peopleCount).map { personNumber ->
                "Person $personNumber — ₦%.2f".format(exactPerPerson)
            }
        } else {
            emptyList()
        }
}

class TipViewModel : ViewModel() {
    private val _state = MutableStateFlow(TipState())
    val state = _state.asStateFlow()

    fun onBillAmountChange(newAmount: Int) {
        _state.update { currentState ->
            currentState.copy(billAmount = newAmount)
        }
    }

    fun onTipPercentChange(newTipPercent: Int) {
        _state.update { currentState ->
            currentState.copy(tipPercent = newTipPercent)
        }
    }

    fun onPeopleCountChange(newPeopleCount: Int) {
        _state.update { currentState ->
            currentState.copy(peopleCount = newPeopleCount)
        }
    }
}