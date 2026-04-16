package com.softgenix.calculator.features.calculator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.softgenix.calculator.features.calculator.domain.usecases.CalculateExpressionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel(
    private val calculateExpressionUseCase: CalculateExpressionUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    private val operadores = listOf("*", "÷", "+", "-")

    fun addValue(valor: String) {
        val state = _uiState.value

        var currentInput = state.currentInput
        var tokens = state.tokens.toMutableList()

        if (currentInput.isEmpty() && valor == ".") {
            currentInput = "0."
        }
        else if (currentInput.isEmpty() && valor in operadores) {
            return
        }
        else if (valor !in operadores) {
            if (valor == ".") {
                if (!currentInput.contains(".")) {
                    currentInput += valor
                }
            } else {
                currentInput += valor
            }
        } else {
            tokens.add(currentInput)
            tokens.add(valor)
            currentInput = ""
        }

        val display = tokens.joinToString("") + currentInput

        _uiState.value = state.copy(
            display = if (display.isEmpty()) "0" else display,
            currentInput = currentInput,
            tokens = tokens
        )
    }

    fun calculate() {
        val state = _uiState.value

        var tokens = state.tokens.toMutableList()
        var currentInput = state.currentInput

        if (currentInput.isNotEmpty()) {
            tokens.add(currentInput)
            currentInput = ""
        }

        if (tokens.isEmpty()) return

        val result = calculateExpressionUseCase(tokens)

        _uiState.value = state.copy(
            display = result,
            currentInput = result,
            tokens = emptyList()
        )
    }
}