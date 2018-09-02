package com.example.ruico.safetyplus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLanguage: ImageButton = findViewById(R.id.buttonLanguage)
        buttonLanguage.setOnClickListener {
            val intent = Intent(this, Language::class.java)
            startActivity(intent)


        }
        val buttonOil: ImageButton = findViewById(R.id.buttonOil)
        buttonOil.setOnClickListener{
            val intent = Intent( this, Oil::class.java)
            startActivity(intent)

        }
        val buttonDriveStart: ImageButton = findViewById(R.id.driveStart)
        buttonDriveStart.setOnClickListener {
            val intent = Intent(this, Drive::class.java)
            startActivity(intent)
        }
    }
}