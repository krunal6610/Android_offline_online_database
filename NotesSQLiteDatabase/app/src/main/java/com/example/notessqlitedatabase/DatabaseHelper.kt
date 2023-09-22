package com.example.notessqlitedatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, db_name, null, db_varsion) {

    companion object {
        private val db_name = "student"
        private val db_varsion = 1
        private val table_name = "data"
        private val id = "id"
        private val name = "name"
        private val details = "details"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_table =
            "CREATE TABLE $table_name($id INTEGER PRIMARY KEY AUTOINCREMENT,$name TEXT,$details TEXT);"
        db?.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "DROP TABLE IF EXISTS $table_name"
        db?.execSQL(drop_table)
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getAllStudents(): List<StudentModel> {
        val list = ArrayList<StudentModel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $table_name"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val s = StudentModel()
                    s.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(id)))
                    s.name = cursor.getString(cursor.getColumnIndex(name))
                    s.details = cursor.getString(cursor.getColumnIndex(details))
                    list.add(s)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        return list
    }

    fun addStudent(s: StudentModel):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
//        values.put(id, s.id)
        values.put(name,s.name)
        values.put(details,s.details)
        val success  = db.insert(table_name,null,values)
        db.close()
        return (Integer.parseInt("$success")!=-1)
    }

    @SuppressLint("Range")
    fun getStudentById(sid:Int):StudentModel{
        val s = StudentModel()
        val db = writableDatabase
        val select = "SELECT * FROM $table_name WHERE $id = $sid"
        val cursor = db.rawQuery(select,null)

        cursor?.moveToFirst()
        s.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(id)))
        s.name = cursor.getString(cursor.getColumnIndex(name))
        s.details = cursor.getString(cursor.getColumnIndex(details))
        cursor.close()
        return s
    }

    fun deleteStudent(sid:Int):Boolean{
        val db = this.writableDatabase
        val success = db.delete(table_name,id+"=?", arrayOf(sid.toString())).toLong()
        db.close()
        return (Integer.parseInt("$success")!=-1)
    }

    fun updateStudent(s:StudentModel):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(name, s.name)
        values.put(details,s.details)
        val success = db.update(table_name,values,id+"=?", arrayOf(s.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success")!=-1
    }
}