package com.example.spendtracker

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {
    //function to test. will test the case, when the input given is valid
     @Test
     fun whenInputIsValid() {
         val amount = 100
         val desc = "Some random desc"
         val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(true)
     }

    @Test
    fun whenInputIsInValid() {
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenInput() {
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(true)
    }

}