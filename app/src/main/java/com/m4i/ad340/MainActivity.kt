package com.m4i.ad340

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

    // region of setup methods
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val zipCodeEditText: EditText = findViewById(R.id.zipCodeEditText)

        //Example of button click event
        val enterButton: Button = findViewById(R.id.ButtonSubmitZipCode)
        enterButton.setOnClickListener {

            val zipCode: String = zipCodeEditText.text.toString()

            if (zipCode.length != 5) {
                Toast.makeText(this, R.string.zipcode_message_error, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, zipCode, Toast.LENGTH_SHORT).show()
                forecastRepository.loadForecast(zipCode)
            }
        }

        val forecastList: RecyclerView = findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter() { forecastItem ->
            val msg = getString(
                R.string.forecast_clicked_format,
                forecastItem.temp,
                forecastItem.description
            )
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        forecastList.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
            //Toast created just to see if we can see on the output
            //Toast.makeText(this, "Loaded itens", Toast.LENGTH_LONG).show()

        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }
}