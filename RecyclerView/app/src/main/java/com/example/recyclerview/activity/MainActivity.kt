package com.example.recyclerview.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.Database
import com.example.recyclerview.R
import com.example.recyclerview.models.Student

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Database.instance
        findViewById<TextView>(R.id.amount_of_students).text = db.size().toString()

        findViewById<Button>(R.id.add_student).setOnClickListener {

            var name = findViewById<EditText>(R.id.name_of_student).text.toString()
            var surname = findViewById<EditText>(R.id.surname_of_student).text.toString()
            val student = Student(name, surname)

            if (name == "" || surname == "") {
                Toast.makeText(this, "Введите необходимые данные!", Toast.LENGTH_SHORT).show()
            } else if (db.checkExisting(student)) {
                Toast.makeText(this, "Студент уже существует!", Toast.LENGTH_SHORT).show()
            } else {
                db.addStudent(student)
                Toast.makeText(this, "Студент успешно добавлен!", Toast.LENGTH_SHORT).show()
                findViewById<TextView>(R.id.amount_of_students).text = db.size().toString()
            }

            try {
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            } catch (e: Exception) {
            }
        }
        findViewById<Button>(R.id.next_page).setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}