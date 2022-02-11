package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var cameraM : CameraManager
    private lateinit var powerBtn : ImageButton
    var isFlash = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        button.setOnClickListener {
            flashFunc(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashFunc(v: View?) {
        if(!isFlash){
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId, true)
            isFlash = true
            button.setImageResource(R.drawable.power_on)
        }
        else{
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId, false)
            isFlash = false
            button.setImageResource(R.drawable.power_off)
        }
    }
}