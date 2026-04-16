package com.softgenix.calculator.features.calculator.presentation.viewmodels

data class CalculatorUiState(
    val display: String = "0",
    val currentInput: String = "",
    val tokens: List<String> = emptyList()
)
