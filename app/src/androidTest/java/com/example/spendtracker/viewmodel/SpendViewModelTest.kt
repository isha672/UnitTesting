package com.example.spendtracker.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.spendtracker.data.SpendsDatabase
import com.example.spendtracker.data.SpendsTrackerDataSource
import com.example.spendtracker.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpendViewModelTest : TestCase() {

    private lateinit var viewModel: SpendViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // to perform testing synchronously


    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, SpendsDatabase::class.java)
            .allowMainThreadQueries()
            .build()  //for testing purpose, we do not want to switch between threads
        val dataSource = SpendsTrackerDataSource(db.getSpendDao())
        viewModel = SpendViewModel(dataSource)
    }

    @Test
    fun testSpendViewModel() {
        viewModel.addSpend(170, "Bought some flowers")
        viewModel.getLast20Spends()
        val result = viewModel.last20SpendsLiveData.getOrAwaitValue().find {
            it.amount == 170 && it.description == "Bought some flowers"
        }  //we need to get this value from the live data and to do this we need to create one more separate function
        assertThat(result != null).isTrue()
    }


}