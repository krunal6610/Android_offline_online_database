package com.example.notessqlitedatabase

class StudentModel {
    var id : Int = 0
    var name :String = ""
    var details:String = ""
    override fun toString(): String {
        return "StudentModel(id=$id, name='$name', details='$details')"
    }

}
