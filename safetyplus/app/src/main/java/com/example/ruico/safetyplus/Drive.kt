package com.example.ruico.safetyplus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_drive.*

class Drive : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val initOil:Int = R.drawable.regular

        OilNozzle.setImageResource(sharedPreferences.getInt("OilCode", initOil))
    }
}
