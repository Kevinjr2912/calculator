package com.softgenix.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softgenix.calculator.features.calculator.domain.usecases.CalculateExpressionUseCase
import com.softgenix.calculator.features.calculator.presentation.screens.CalculatorScreen
import com.softgenix.calculator.features.calculator.presentation.viewmodels.CalculatorViewModel
import com.softgenix.calculator.features.calculator.presentation.viewmodels.CalculatorViewModelFactory
import com.softgenix.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val calculateExpressionUseCase = remember { CalculateExpressionUseCase() }

                val viewModelFactory = remember { CalculatorViewModelFactory(calculateExpressionUseCase) }

                val calculatorViewModel: CalculatorViewModel = viewModel (factory = viewModelFactory)

                CalculatorScreen(calculatorViewModel = calculatorViewModel)
            }
        }

    }
}
