package com.example.recyclerview

import android.util.Log
import com.example.recyclerview.models.Student
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Database() {
    private val students= arrayListOf<Student>(Student("Assem1", "Abdirashova"),Student("Assem2", "Abdirashova"),Student("Assem3", "Abdirashova"),Student("Assem4", "Abdirashova"))
    private val map = HashMap<String, Student>()

    companion object {
        val instance = Database()
    }

    fun mockedData(){
        for(i in 1..10){
            val student = Student("Assem ${i}", "Shyngyskyzy")
            map[student.name] = student
            students.add(student)
        }
    }
    fun getStudents(): ArrayList<Student>? {
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
    fun addStudent(s: Student){
        students.add(s)
        map[s.name] = s
    }
    fun size(): Int{
        return students.size
    }

}