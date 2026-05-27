package com.example.tipcalculatorpractice

data class TipState(
    val billAmount: Int = 0,
    val tipPercent: Int = 0,
    val peopleCount: Int = 0
) {
    val tipPerPerson: Int
        get() = if (peopleCount > 0) {
            (billAmount * tipPercent / 100) / peopleCount
        } else {
            0
        }

    val totalPerPerson: Int
        get() = if (peopleCount > 0) {
            (billAmount / peopleCount) + tipPerPerson
        } else {
            0
        }

    val label: String
        get() = when {
            tipPercent >= 25 -> "Generous"
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