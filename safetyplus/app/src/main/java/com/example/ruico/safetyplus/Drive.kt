package com.example.ruico.safetyplus

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_drive.*
import java.io.InputStream
import java.util.*

class Drive : AppCompatActivity() {
    private val uuid = UUID.fromString("a11f255d-ea48-5aa2-1a34-3b1f7a762d13")
    private val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private lateinit var inputStream: InputStream
    private var socket: BluetoothSocket? = null
    private lateinit var serverSocket: BluetoothServerSocket
    private lateinit var mDevice: BluetoothDevice
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val initOil:Int = R.drawable.regular

        OilNozzle.setImageResource(sharedPreferences.getInt("OilCode", initOil))

        val address = "B8:27:EB:3D:B0:67"

        Thread(object : Runnable {
            private var len: Int = 0
            private var buf = ByteArray(256)
            private lateinit var stat: Array<String>
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
                    stat = str.split(",".toRegex(), 3).toTypedArray()

                    handler.post {
                        fuelListener(stat[0])
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

    public override fun onDestroy() {
        super.onDestroy()
        try {
            serverSocket.close()
        }
        finally {

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
}
