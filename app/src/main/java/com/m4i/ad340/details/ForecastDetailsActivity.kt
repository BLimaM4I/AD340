package com.m4i.ad340.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.m4i.ad340.R
import com.m4i.ad340.TempDisplaySettingManager
import com.m4i.ad340.formatTempForDisplay
import com.m4i.ad340.showTempDisplaySettingsDialog

class ForecastDetailsActivity : AppCompatActivity() {

    //variable that will be assign later on the program
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        setTitle(R.string.forecast_details)

        val tempText = findViewById<TextView>(R.id.NewActivityTempText)
        val descriptionText = findViewById<TextView>(R.id.NewActivityDescriptionText)

        val temp = intent.getFloatExtra("key_temp", 0f)
        tempText.text =
            formatTempForDisplay(temp, tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text = intent.getStringExtra("key_description")
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