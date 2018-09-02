package com.example.ruico.safetyplus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_oil.*
import android.preference.PreferenceManager

class Oil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oil)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()

        buttonRegular.setOnClickListener{
            editor.putString("OilCode", "Regular")
            editor.commit()
            finish()
        }

        buttonHighOctane.setOnClickListener{
            editor.putString("OilCode", "HighOctane")
            editor.commit()
            finish()
        }

        buttonDiesel.setOnClickListener{
            editor.putString("OilCode", "Diesel")
            editor.commit()
            finish()
        }
    }


}
