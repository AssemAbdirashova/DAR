package com.example.recyclerview.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.Database
import com.example.recyclerview.R
import com.example.recyclerview.StudentListAdapter
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var db = Database.instance

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = db.getStudents()?.let { StudentListAdapter(it) }
    }
}