package com.m4i.ad340

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m4i.ad340.details.ForecastDetailsActivity

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    // region of setup methods
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

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
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) { forecast ->
            showForecastDetails(forecast)
        }
        forecastList.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
            //Toast created just to see if we can see on the output
            //Toast.makeText(this, "Loaded itens", Toast.LENGTH_LONG).show()

        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }

    private fun showForecastDetails(forecast: DailyForecast) {
        val forecastDetailsIntent = Intent(this, ForecastDetailsActivity::class.java)
        forecastDetailsIntent.putExtra("key_temp", forecast.temp)
        forecastDetailsIntent.putExtra("key_description", forecast.description)
        startActivity(forecastDetailsIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Handle item selection
        return when (item.itemId) {
            R.id.tempDisplaySetting -> {
                showTempDisplaySettingsDialog(this, tempDisplaySettingManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}