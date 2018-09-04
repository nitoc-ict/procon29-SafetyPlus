package com.example.ruico.safetyplus

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*

class Language : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val config = Configuration()

        buttonJp.setOnClickListener {
            config.setLocale(Locale.JAPAN)
            resources.updateConfiguration(config, baseContext.resources.displayMetrics)
            finish()
        }

        buttonKr.setOnClickListener{
            config.setLocale(Locale.KOREA)
            resources.updateConfiguration(config, baseContext.resources.displayMetrics)
            finish()
        }

        buttonZh.setOnClickListener {
            config.setLocale(Locale.CHINA)
            resources.updateConfiguration(config, baseContext.resources.displayMetrics)
            finish()
        }

        buttonEn.setOnClickListener {
            config.setLocale(Locale.ENGLISH)
            resources.updateConfiguration(config, baseContext.resources.displayMetrics)
            finish()
        }
    }
}
