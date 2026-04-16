package com.softgenix.calculator.features.calculator.domain.usecases

class CalculateExpressionUseCase {

    operator fun invoke(values: List<String>): String {
        if (values.isEmpty()) return "0"

        val mutableValues = values.toMutableList()

        resolve(mutableValues, listOf("*", "÷"))
        resolve(mutableValues, listOf("+", "-"))

        return mutableValues.firstOrNull() ?: "0"
    }

    private fun resolve(
        values: MutableList<String>,
        operators: List<String>
    ) {
        var i = 0
        while (i < values.size) {
            if (values[i] in operators) {
                val a = values[i - 1].toDoubleOrNull() ?: 0.0
                val b = values[i + 1].toDoubleOrNull() ?: 0.0

                val result = when (values[i]) {
                    "*" -> a * b
                    "÷" -> if (b != 0.0) a / b else 0.0
                    "+" -> a + b
                    "-" -> a - b
                    else -> 0.0
                }

                // eliminar número, operador, número
                values.removeAt(i - 1)
                values.removeAt(i - 1)
                values.removeAt(i - 1)

                // insertar resultado
                values.add(i - 1, result.toString())

                i--
            }
            i++
        }
    }
}
