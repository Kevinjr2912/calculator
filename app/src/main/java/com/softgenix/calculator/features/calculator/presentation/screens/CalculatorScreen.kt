package com.softgenix.calculator.features.calculator.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softgenix.calculator.features.calculator.presentation.components.CalculatorButton
import com.softgenix.calculator.features.calculator.presentation.viewmodels.CalculatorViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorScreen (calculatorViewModel: CalculatorViewModel = viewModel()) {
    val uiState by calculatorViewModel.uiState.collectAsStateWithLifecycle()
    val displayText = uiState.display

    val values = remember { listOf("7","8","9","÷","4","5","6","x","1","2","3","-","0",".","=","+") }

    val scrollState = rememberScrollState()

    LaunchedEffect(displayText) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            TextField(
                value = displayText,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().horizontalScroll(scrollState),
                readOnly = true,
                textStyle = TextStyle(
                    fontSize = 45.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )

            Column(
                modifier = Modifier.padding(bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                val rows = values.chunked(4)

                rows.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (value in row) {
                            CalculatorButton(
                                value = value,
                                isOperator = value in setOf("÷", "x", "+", "-", "="),
                                onClick = { clickedValue ->
                                    when (clickedValue) {
                                        "=" -> calculatorViewModel.calculate()
                                        "x" -> calculatorViewModel.addValue("*")
                                        else -> calculatorViewModel.addValue(clickedValue)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }


}