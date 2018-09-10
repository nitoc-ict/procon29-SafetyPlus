package com.example.ruico.safetyplus

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_drive.*
import java.io.InputStream
import java.util.*

class DriveActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private val uuid = UUID.fromString("a11f255d-ea48-5aa2-1a34-3b1f7a762d13")
    private val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private lateinit var inputStream: InputStream
    private var socket: BluetoothSocket? = null
    private lateinit var serverSocket: BluetoothServerSocket
    private lateinit var mDevice: BluetoothDevice
    private var handler = Handler()
    private lateinit var tts: TTS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val initOil: Int = R.drawable.regular
        tts = TTS(this, this)
        OilNozzle.setImageResource(sharedPreferences.getInt("OilCode", initOil))

        val address = "B8:27:EB:3D:B0:67"

        Thread(object : Runnable {
            private var len: Int = 0
            private var buf = ByteArray(256)
            override fun run() {
                try {
                    mDevice = mBluetoothAdapter.getRemoteDevice(address)
                    socket = mDevice.createRfcommSocketToServiceRecord(uuid)
                    socket!!.connect()
                    inputStream = socket!!.inputStream
                }
                finally {

                }

                while (true) {
                    Arrays.fill(buf,0.toByte())
                    len = inputStream.read(buf)
                    val str = String(buf)
                    val stat = str.split(",".toRegex(), 3).toTypedArray()

                    handler.post {
                        fuelListener(stat[0])
                        turnListener(stat[1])
                        signListener(stat[2], stat[1])
                    }
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }
        }).start()
    }

    override fun onInit(status: Int) {
        tts.checkInit(status)
    }

    public override fun onDestroy() {
        super.onDestroy()
        tts.finish()
        try {
            serverSocket.close()
        }
        finally {
            finish()
        }
    }

    fun fuelListener(str: String) {
        if (str.equals("refuel", false) && OilNozzle.visibility == View.INVISIBLE){
            OilNozzle.visibility = View.VISIBLE
        }
        else if (str.equals("null", false) && OilNozzle.visibility == View.VISIBLE){
            OilNozzle.visibility = View.INVISIBLE
        }
    }

    fun turnListener(str: String) {
        val lpLeft = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
        val lpRight = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
        lpLeft.weight = 0f
        lpRight.weight = 0f
        if (str.equals("left", false) && lpLeft.weight == 0f){
            tts.say(getString(R.string.turn))
            lpLeft.weight = 3f
            lpRight.weight = 0f
        }
        else if (str.equals("right", false) && lpRight.weight == 0f){
            tts.say(getString(R.string.turn))
            lpRight.weight = 3f
            lpLeft.weight = 0f
        }
        else if (str.equals("null", false) && (lpRight.weight + lpLeft.weight) != 0f){
            lpRight.weight = 0f
            lpLeft.weight = 0f
        }

        turnRight.layoutParams = lpRight
        turnLeft.layoutParams = lpLeft
    }

    fun signListener(str: String, turn: String){
        val sign = str.split(',')
        val lpStop = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
        val lpSlow = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
        val lpOver = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
        val turnStop = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        val turnSlow = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        val turnOver = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)

        val lpSign = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)

        lpStop.weight = 0f
        turnStop.weight = 0f
        lpSlow.weight = 0f
        turnStop.weight = 0f
        lpOver.weight = 0f
        turnOver.weight = 0f

        lpSign.weight = 0f

        if (sign[0].equals("null", false) && sign[1].equals("null", false) && sign[2].equals("null", false)){
            lpSign.weight = 0f
        }
        else {
            lpSign.weight = 1f
        }

        if (sign[0].equals("stop", false) && (lpStop.weight + turnStop.weight) == 0f){
            lpStop.weight = 1f
            turnStop.weight = 1f
        }
        else if (sign[0].equals("null", false) && (lpStop.weight + turnStop.weight) != 0f){
            lpStop.weight = 0f
            turnStop.weight = 0f
        }

        if (sign[1].equals("slow", false) && (lpSlow.weight + turnSlow.weight) == 0f){
            lpSlow.weight = 1f
            turnSlow.weight = 1f
        }
        else if (sign[1].equals("null", false) && (lpSlow.weight + turnSlow.weight) != 0f){
            lpSlow.weight = 0f
            turnSlow.weight = 0f
        }

        if (sign[2].equals("over", false) && (lpOver.weight + turnOver.weight) == 0f){
            lpOver.weight = 1f
            turnOver.weight = 1f
        }
        else if (sign[2].equals("null", false) && (lpOver.weight + turnOver.weight) != 0f){
            lpOver.weight = 0f
            turnOver.weight = 0f
        }


        signLayout.layoutParams = lpSign

        if (turn.equals("null",false)){
            signLayout.orientation = LinearLayout.HORIZONTAL
            stop.layoutParams = lpStop
            slow.layoutParams = lpSlow
            overtaking.layoutParams = lpOver
        }
        else {
            signLayout.orientation = LinearLayout.VERTICAL
            stop.layoutParams = turnStop
            slow.layoutParams = turnSlow
            overtaking.layoutParams = turnOver
        }
    }
}
