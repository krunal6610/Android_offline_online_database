package com.example.firebase_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDataActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var submitButton: Button

    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        submitButton = findViewById(R.id.submitBtn)

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        submitButton.setOnClickListener {

            var name = edtName.text.toString()
            var email = edtEmail.text.toString()
            var password = edtPassword.text.toString()

            var id = databaseReference.push().key!!

            var user = Model(id, name, email, password)

            databaseReference.child(id).setValue(user)

                .addOnSuccessListener {



                    edtName.text.clear()
                    edtEmail.text.clear()
                    edtPassword.text.clear()
        Toast.makeText(this,"Sucess",Toast.LENGTH_SHORT).show()



                }

                .addOnFailureListener {

                    Toast.makeText(this,"Failure",Toast.LENGTH_SHORT).show()

                }


        }


    }
}