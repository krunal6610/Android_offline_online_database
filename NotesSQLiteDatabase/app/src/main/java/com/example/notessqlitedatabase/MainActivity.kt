package com.example.notessqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var addBTN: Button
    lateinit var recyclerView:RecyclerView
    var adapter:StudentListAdapter? = null
    var dbHandler:DatabaseHelper? = null
    var slit : List<StudentModel> = ArrayList<StudentModel>()
    var linearLayoutManager:LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBTN = findViewById(R.id.add_Student_btn)
        recyclerView  = findViewById(R.id.recycler_view_id)
        dbHandler = DatabaseHelper(this)
        fetchList()
        addBTN.setOnClickListener {
            val i = Intent(this@MainActivity,AddStudent::class.java)
            startActivity(i)
        }
    }

    private fun fetchList() {
        slit = dbHandler!!.getAllStudents()
        adapter = StudentListAdapter(slit,applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        adapter?.notifyDataSetChanged()
    }
}