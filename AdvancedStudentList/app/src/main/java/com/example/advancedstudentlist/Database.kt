package com.example.recyclerview

import android.util.Log
import com.example.advancedstudentlist.models.Student

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Database() {
    private val students= listOf<Student>()
    private val map = HashMap<String?, Student>()

    companion object {
        val instance = Database()
    }

    fun mockedData(){
        //for(i in 1..10){
          //  val student = Student("Assem ${i}", "Shyngyskyzy")
           // map[student.name] = student
            //students.add(student)
        //}
    }
    fun getStudents(): List<Student>? {
        return this.students
    }
    fun checkExisting(student: Student): Boolean {
        for(s in students){
            if(map[s.name] == student){
                return true
            }
        }
        return false
    }
    fun addStudent(sy: Student){
        //students.add(sy)
        map[sy.name] = sy
    }
    fun size(): Int{
        return students.size
    }

}