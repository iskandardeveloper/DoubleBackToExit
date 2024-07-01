package dev.iskandar.doublebacktoexit

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.BLACK

        handler = Handler(Looper.getMainLooper())
        onBackPressedDispatcher.addCallback(onBackPressedCallBack)

    }

    val runnable = object : Runnable {
        override fun run() {
            doubleBackToExitPressedOnce = false
            handler.postDelayed(this, 2000)
        }
    }

    private val onBackPressedCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!doubleBackToExitPressedOnce) {
                Toast.makeText(
                    this@MainActivity,
                    "Please click BACK again to exit",
                    Toast.LENGTH_SHORT
                ).show()
                doubleBackToExitPressedOnce = true
                handler.postDelayed(runnable, 2000)
            } else {
                finish()
            }
        }
    }
}