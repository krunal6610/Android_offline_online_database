package com.example.firebase_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addDataBtn:Button
    lateinit var showDataBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addDataBtn = findViewById(R.id.addDataBtn)
        showDataBtn = findViewById(R.id.showDataBtn)

        addDataBtn.setOnClickListener {

            var i = Intent(this,AddDataActivity::class.java)
            startActivity(i)

        }

        showDataBtn.setOnClickListener {

            var i = Intent(this,ShowDataActivity::class.java)
            startActivity(i)

        }


    }
}