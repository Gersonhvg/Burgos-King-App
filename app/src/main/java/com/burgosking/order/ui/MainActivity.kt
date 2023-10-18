package com.burgosking.order.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.burgosking.order.R
import com.burgosking.order.ui.auth.loginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, loginActivity::class.java)
        startActivity(intent)
        finish()
    }
}