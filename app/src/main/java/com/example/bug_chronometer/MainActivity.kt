package com.example.bug_chronometer

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.Chronometer
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private var _chronometer: Chronometer? = null
    private val chronometer
        get() = _chronometer!!

    private var _produceBugButton: MaterialButton? = null
    private val produceBugButton
        get() = _produceBugButton!!

    private var _fl: FrameLayout? = null
    private val fl
        get() = _fl!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _chronometer = Chronometer(this)
        _produceBugButton = MaterialButton(this)
        _fl = FrameLayout(this)

        fl.apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT,
            )
            addView(produceBugButton)
        }

        produceBugButton.apply {
            (layoutParams as FrameLayout.LayoutParams).apply {
                width = FrameLayout.LayoutParams.WRAP_CONTENT
                height = FrameLayout.LayoutParams.WRAP_CONTENT
                gravity = Gravity.CENTER_VERTICAL + Gravity.CENTER_HORIZONTAL
            }
            text = "Produce the bug"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 32F)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setOnClickListener {
                    produceTheBug()
                }
            } else {
                setOnClickListener {
                    Toast.makeText(
                        context, "Your device cannot produce the bug", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        setContentView(fl)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun produceTheBug() {
        // This code will open the YouTube application
        chronometer.isTheFinalCountDown
    }

    override fun onDestroy() {
        super.onDestroy()

        _chronometer = null
        _produceBugButton = null
        _fl = null
    }

}