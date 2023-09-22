package com.example.notessqlitedatabase

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
class AddStudent : AppCompatActivity() {

    lateinit var save_btn:Button
    lateinit var deleteBTN:Button
    lateinit var nameEditText:EditText
    lateinit var detailsEditText:EditText
    var dbHandler:DatabaseHelper? = null
    var isEditeMode:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        save_btn = findViewById(R.id.save_btn)
        deleteBTN = findViewById(R.id.delete_btn)
        nameEditText = findViewById(R.id.name_editText)
        detailsEditText  = findViewById(R.id.details_editText)
        dbHandler = DatabaseHelper(this)
        if(intent!=null && intent.getStringExtra("mode") == "e"){
            isEditeMode = true
            save_btn.text = "Update data"
            deleteBTN.visibility = View.VISIBLE
            val s:StudentModel =dbHandler!!.getStudentById(intent.getIntExtra("id",0))
            nameEditText.setText(s.name)
            detailsEditText.setText(s.details)
        }
        else{
            isEditeMode = false
            save_btn.text = "save data"
            deleteBTN.visibility = View.INVISIBLE

        }

        save_btn.setOnClickListener {
            var succes:Boolean = false
            val s:StudentModel = StudentModel()
            if(isEditeMode){
                s.id = intent.getIntExtra("id",0)
                s.name = nameEditText.text.toString()
                s.details =detailsEditText.text.toString()

                succes = dbHandler?.updateStudent(s) as Boolean
            }
            else{
                s.name = nameEditText.text.toString()
                s.details = detailsEditText.text.toString()
                succes = dbHandler?.addStudent(s) as Boolean
            }
            if(succes){
                val i = Intent(applicationContext,MainActivity::class.java)
                startActivity(i)
                finish()
            }
            else{
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
            }
        }
        deleteBTN.setOnClickListener {
            val dialogue = AlertDialog.Builder(this).setTitle("DElete data ?").setMessage("Are you sure want to delete ?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    val succes = dbHandler?.deleteStudent(intent.getIntExtra("id",0)) as Boolean
                    if(succes)
                        finish()
                    dialog.dismiss()
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
            dialogue.show()
        }

    }
}