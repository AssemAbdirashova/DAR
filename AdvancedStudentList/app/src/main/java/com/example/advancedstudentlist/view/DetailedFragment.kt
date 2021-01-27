package com.example.advancedstudentlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.advancedstudentlist.R
import com.example.advancedstudentlist.models.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailedFragment : Fragment() {

    private var student: Student? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvId = view.findViewById<TextView>(R.id.detailed_id)
        val tvName = view.findViewById<TextView>(R.id.detailed_name)
        val tvSurname = view.findViewById<TextView>(R.id.detailed_surname)
        val tvGrade = view.findViewById<TextView>(R.id.detailed_grade)
        val ivImage = view.findViewById<TextView>(R.id.detailed_image)

        arguments.let {
            student = DetailedFragmentArgs.fromBundle(it!!).student
        }

        tvId.text = student?.id.toString()
        tvName.text = student?.name
        tvSurname.text = student?.surname
        tvGrade.text = student?.grade.toString()
    }
}