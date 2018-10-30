package com.example.ruico.safetyplus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLanguage.setOnClickListener {
            val intent = Intent()
            intent.action = Settings.ACTION_LOCALE_SETTINGS
            startActivity(intent)
        }

        buttonOil.setOnClickListener{
            val intent = Intent(this, OilActivity::class.java)
            startActivity(intent)
        }

        buttonDrive.setOnClickListener {
            val intent = Intent(this, DriveActivity::class.java)
            startActivity(intent)
        }
    }
}