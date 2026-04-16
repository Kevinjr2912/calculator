package com.softgenix.calculator.features.calculator.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    value: String,
    isOperator: Boolean,
    onClick: (String) -> Unit
) {
    Button(
        onClick = { onClick(value) },
        modifier = Modifier.width(80.dp).height(80.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = if (isOperator) Color(0xFFFD9403) else Color(0xFF323333))
    ) {
        Text(
            text = value,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Monospace
        )
    }
}