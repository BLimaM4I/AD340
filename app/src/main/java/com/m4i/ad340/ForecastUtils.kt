package com.m4i.ad340

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String {
    return when (tempDisplaySetting) {
        TempDisplaySetting.Fahrenheit -> String.format("%.2f ºF", temp)
        TempDisplaySetting.Celsius -> {
            val temp = (temp - 32f) * (5f / 9f)
            String.format("%.2f ºC", temp)
        }
    }
}

fun showTempDisplaySettingsDialog(
    context: Context,
    tempDisplaySettingManager: TempDisplaySettingManager
) {
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Chose Display Units")
        .setMessage("Chose which temperature unit to use for temperature display")
        .setPositiveButton("ºF") { _, _ ->
            tempDisplaySettingManager.updateSettings(TempDisplaySetting.Fahrenheit)
        }
        .setNeutralButton("ºC") { _, _ ->
            tempDisplaySettingManager.updateSettings(TempDisplaySetting.Celsius)
        }
        .setOnDismissListener {
            Toast.makeText(
                context,
                "Settings will take effect on next restart",
                Toast.LENGTH_SHORT
            ).show()
        }
    dialogBuilder.show()
}