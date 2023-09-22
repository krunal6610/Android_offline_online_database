package com.example.sharedprference

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var email_txt: TextView
    lateinit var password_txt: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        email_txt =findViewById(R.id.txt_email_id)
        password_txt =findViewById(R.id.txt_password_id)

        var sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)

        var getEmail = sharedPreferences.getString("email","")
        var getPassword = sharedPreferences.getString("password","")

        email_txt.text = getEmail
        password_txt.text = getPassword


    }
}