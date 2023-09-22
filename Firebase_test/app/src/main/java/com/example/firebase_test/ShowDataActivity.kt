package com.example.firebase_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Objects

class ShowDataActivity : AppCompatActivity() {

    lateinit var rv:RecyclerView
    lateinit var pb:ProgressBar

    lateinit var mList:ArrayList<Model>
    lateinit var adapter:RvAdapter
    lateinit var mReferernce:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        rv = findViewById(R.id.rv)
        pb = findViewById(R.id.pb)

        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(this,2)

        rv.visibility = View.GONE
        pb.visibility = View.VISIBLE

        mList = ArrayList<Model>()

        mReferernce = FirebaseDatabase.getInstance().getReference("Users")

       mReferernce.addValueEventListener(object :ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {

               if (snapshot.exists()){


                   for (user in snapshot.children){

                       var data = user.getValue(Model::class.java)

                       mList.add(data!!)



                   }

                   adapter = RvAdapter(mList,this@ShowDataActivity)

                   rv.adapter = adapter

                   rv.visibility = View.VISIBLE
                   pb.visibility = View.GONE

               }



           }

           override fun onCancelled(error: DatabaseError) {

               Toast.makeText(this@ShowDataActivity,"Error",Toast.LENGTH_SHORT).show()
               rv.visibility = View.VISIBLE
               pb.visibility = View.GONE

           }


       })





    }
}