package com.example.spendtracker

object Validator {
    // to validate user inputs
    fun validateInput(amount: Int, desc: String): Boolean {
        //first we will write the test case for this function before its implementation
        return !(amount <= 0 || desc.isEmpty())
    }
}