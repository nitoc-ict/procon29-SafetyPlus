package com.example.ruico.safetyplus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_oil.*
import android.preference.PreferenceManager

class OilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oil)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()

        buttonRegular.setOnClickListener{
            editor.putInt("OilCode", R.drawable.regular)
            editor.apply()
            finish()
        }

        buttonHighOctane.setOnClickListener{
            editor.putInt("OilCode", R.drawable.highoctane)
            editor.apply()
            finish()
        }

        buttonDiesel.setOnClickListener{
            editor.putInt("OilCode", R.drawable.diesel)
            editor.apply()
            finish()
        }
    }


}
