package com.example.tictac

import android.util.Log

object UserRepository {
    private val users = mutableListOf<User>()
    fun getUsers() = users
    private val username = mutableSetOf<String>()

    fun addStudent(name: String?): Boolean {
        val contains = username.contains(name)
        if (!contains) {
            val student = User(name =  name)
            users.add(student)
            Log.d("List", "Insert: ${student.id} ${users}")
        }
        username.add(name!!)
        return contains
    }

    fun getUser(name: String?): User {
        return users.find { student -> student.name == name } ?: User(name = name)
    }
}