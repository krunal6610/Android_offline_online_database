package com.example.sharedprference

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var btnLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.email_id)
        password = findViewById(R.id.password_id)
        btnLogin = findViewById(R.id.btnLogin_id)

        var sharedPreferences = getSharedPreferences("myPreferences",Context.MODE_PRIVATE)
        var userEmail = sharedPreferences.getString("email","").toString()
        var userPassword = sharedPreferences.getString("password","").toString()

        if (userEmail != "" && userPassword != ""){
            startActivity(Intent(this,HomeActivity::class.java))
        }

        btnLogin.setOnClickListener(View.OnClickListener {
            var editor = sharedPreferences.edit()
            editor.putString("email",email.text.toString())
            editor.putString("password",password.text.toString())
            editor.apply()
            startActivity(Intent(this,HomeActivity::class.java))
        })

    }
}