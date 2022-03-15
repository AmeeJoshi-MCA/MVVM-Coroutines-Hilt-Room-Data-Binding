package com.myapplication.network

import androidx.test.platform.app.InstrumentationRegistry
import com.myapplication.application.Application
import dagger.hilt.android.testing.HiltTestApplication
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.IOException
import java.io.InputStreamReader

open class BaseTest {

    val mockWebServer = MockWebServer()

    fun enqueue(fileName: String) {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(readStringFromFile(fileName))
        )
    }

    private fun readStringFromFile(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as HiltTestApplication).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}