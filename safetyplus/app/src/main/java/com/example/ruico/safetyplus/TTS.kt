package com.example.ruico.safetyplus

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TTS (context: Context, listener: TextToSpeech.OnInitListener){
    private var tts = TextToSpeech(context, listener)

    fun checkInit(status: Int) {
        if (TextToSpeech.SUCCESS == status) {
            val locale = Locale.getDefault()
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.language = locale
            }
        }
    }

    fun say(str: String){
        tts.speak(str, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}


