package ru.myhousingservice.myservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    button.setOnClickListener {
        val DashboardIntent = Intent(this@LoginActivity,
            MainActivity::class.java)
        startActivityForResult(DashboardIntent)
    }
}