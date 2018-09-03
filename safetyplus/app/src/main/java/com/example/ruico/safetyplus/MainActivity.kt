package com.example.ruico.safetyplus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val oil: String = sharedPreferences.getString("OilCode", "Regular")
        textView.text = oil

        buttonLanguage.setOnClickListener {
            val intent = Intent(this, Language::class.java)
            startActivity(intent)
        }

        buttonOil.setOnClickListener{
            val intent = Intent( this, Oil::class.java)
            startActivity(intent)
        }

        buttonDrive.setOnClickListener {
            val intent = Intent(this, Drive::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val oil: String = sharedPreferences.getString("OilCode", "Regular")
        textView.text = oil
    }
}