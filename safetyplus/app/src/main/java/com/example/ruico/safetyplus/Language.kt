package com.example.ruico.safetyplus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*

class Language : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        buttonJp.setOnClickListener {
            Locale.setDefault(Locale.JAPAN)

            finish()
        }

        buttonCh.setOnClickListener {
            Locale.setDefault(Locale.CHINA)

            finish()
        }

        buttonKr.setOnClickListener{
            Locale.setDefault(Locale.KOREA)

            finish()
        }

        buttonEn.setOnClickListener {
            Locale.setDefault(Locale.ENGLISH)

            finish()
        }
    }
}
