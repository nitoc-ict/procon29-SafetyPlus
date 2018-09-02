package com.example.ruico.safetyplus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_oil.*

class Oil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oil)

        buttonRegular.setOnClickListener{
            finishActivity(1)
        }

        buttonHighOctane.setOnClickListener{
            finishActivity(2)
        }

        buttonDiesel.setOnClickListener{
            finishActivity(3)
        }
    }


}
