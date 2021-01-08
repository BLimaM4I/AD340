package com.m4i.ad340

import android.content.Context

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}

class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSettings(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_display", setting.name).commit()
    }

    fun getTempDisplaySetting(): TempDisplaySetting {
        val settingValue =
            preferences.getString("key_temp_display", TempDisplaySetting.Celsius.name)
                ?: TempDisplaySetting.Celsius.name
        return TempDisplaySetting.valueOf(settingValue)
    }
}