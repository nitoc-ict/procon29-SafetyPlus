package com.example.ruico.safetyplus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}