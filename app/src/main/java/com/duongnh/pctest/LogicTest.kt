package com.duongnh.pctest

fun solution(value1: Int, weight1: Int, value2: Int, weight2: Int, maxW: Int): Int {
    val totalWeight = weight1 + weight2
    if (totalWeight <= maxW) {
        val totalValue = value1 + value2
        return totalValue
    }

    if (weight1 - weight2 >= 0 && weight1 <= maxW) {
        return value1
    } else if (weight2 - weight1 >= 0 && weight2 <= maxW) {
        return value2
    }

    return 0
}

fun main() {
    val testCase1 = solution(10, 5, 6, 4, 8)
    println("Test Case 1 output: $testCase1 -- The output should be: 10")

    val testCase2 = solution(10, 5, 6, 4, 9)
    println("Test Case 2 output: $testCase2 -- The output should be: 16")

    val testCase3 = solution(5, 3, 7, 4, 6)
    println("Test Case 3 output: $testCase3 -- The output should be: 7")

    val testCase4 = solution(5, 4, 7, 4, 6)
    println("Test Case 4 output: $testCase4 -- The output should be: 5")
}