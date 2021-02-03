package com.example.advancedstudentlist

import android.util.Log
import android.widget.Toast
import com.example.advancedstudentlist.models.Student
import java.util.*

class StudentsRepository {
    private val students = mutableListOf<Student>()
    private var restore: Queue<Student> = LinkedList<Student>()
    private val st = Stack<Student>()

    fun getStudents() = students

    fun addStudent(name: String?): Boolean {
        val student = Student(name = name)
        val contains = students.contains(student)
        if (!contains) {
            students.add(student)
            Log.d("List", "Insert: ${student.id} ${students}")
        }
        return contains
    }

    fun getStudent(id: Int): Student {
        return students.find { student -> student.id == id } ?: Student(id)
    }

    fun deleteStudent(student: Student): Boolean {
        if (students.remove(student)) {
            if (restore.size >= 5)
                restore.remove()
            restore.add(student)
            Log.d("List", "Delete: ${student.id} ${students}")
            return true
        }
        return false
    }

    fun restoreStudent(): Boolean {
        while (!restore.isEmpty()) {
            st.push(restore.peek())
            restore.remove()
        }
        if(!st.isEmpty()) {
            students.add(st.peek())
            st.pop()
            return true
        }
        return false
    }
}