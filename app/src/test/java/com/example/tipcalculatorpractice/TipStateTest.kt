package com.example.tipcalculatorpractice

import org.junit.Assert.assertEquals
import org.junit.Test

class TipStateTest {

    @Test
    fun `tipPerPerson is calculated correctly`() {
        // Arrange: 1000 bill, 20% tip = 200 total tip. 2 people.
        val state = TipState(billAmount = 1000, tipPercent = 20, peopleCount = 2)

        // Act & Assert: Tip per person should be 100
        assertEquals(100, state.tipPerPerson)
    }

    @Test
    fun `totalPerPerson is calculated correctly`() {
        // Arrange: 1000 bill, 20% tip = 200 total tip. 2 people.
        val state = TipState(billAmount = 1000, tipPercent = 20, peopleCount = 2)

        // Act & Assert: 500 (bill split) + 100 (tip split) = 600
        assertEquals(600, state.totalPerPerson)
    }

    @Test
    fun `when peopleCount is 0, math defaults to 0 to prevent crashes`() {
        // Arrange: Edge case where user hasn't added people yet
        val state = TipState(billAmount = 1000, tipPercent = 15, peopleCount = 0)

        // Assert
        assertEquals(0, state.tipPerPerson)
        assertEquals(0, state.totalPerPerson)
        assertEquals(emptyList<String>(), state.breakdown)
    }

    @Test
    fun `labels evaluate correctly based on tip percent thresholds`() {
        assertEquals("Give Tip", TipState(tipPercent = 5).label)
        assertEquals("Standard", TipState(tipPercent = 10).label)
        assertEquals("Great", TipState(tipPercent = 15).label)
        assertEquals("Generous", TipState(tipPercent = 25).label)
    }

    @Test
    fun `breakdown formats exact kobo correctly with proper Naira symbol`() {
        // Arrange: Bill 1050, 15% Tip -> 157.50 tip. Grand Total: 1207.50.
        // 2 people -> 603.75 exactly each.
        val state = TipState(billAmount = 1050, tipPercent = 15, peopleCount = 2)

        // Expected formatted output
        val expected = listOf(
            "Person 1 — ₦603.75",
            "Person 2 — ₦603.75"
        )

        // Act & Assert
        assertEquals(expected, state.breakdown)
    }
}