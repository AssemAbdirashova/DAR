package com.example.advancedstudentlist.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedstudentlist.R
import com.example.advancedstudentlist.StudentListAdapter
import com.example.advancedstudentlist.StudentsRepository
import com.example.advancedstudentlist.databinding.FragmentListBinding
import com.example.advancedstudentlist.models.BindingFragment
import com.example.advancedstudentlist.models.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.FieldPosition

class ListFragment : BindingFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val repository = StudentsRepository()

    private lateinit var listener: StudentListAdapter.OnItemClickListener
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etName = view.findViewById<EditText>(R.id.et_name)
        val btnAddStudent = view.findViewById<Button>(R.id.btn_add_student)
        val btnRestoreStudent = view.findViewById<FloatingActionButton>(R.id.btn_restore_student)
        recyclerView = view.findViewById(R.id.student_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        listener = object : StudentListAdapter.OnItemClickListener {
            override fun onStudentSelected(student: Student, position: Int) {
//                val action = student.id?.let { ListFragmentDirections.listToDetail() }
//                if (action != null) {-
//                    view.findNavController().navigate(action)
//                }

                findNavController().navigate(student.id?.let {
                    ListFragmentDirections.listToDetail(
                        it
                    )
                }!!)
                hideKeyboard()
            }
            override fun onStudentDeleted(restStudent: Student, position: Int, adapter: StudentListAdapter) {
               repository.deleteStudent(restStudent)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, adapter.itemCount - position)
            }
        }

        val studentsAdapter = StudentListAdapter(listener)
        recyclerView.adapter = studentsAdapter
        studentsAdapter.submitList(repository.getStudents())
        btnAddStudent.setOnClickListener {
            val student = Student(name = etName.text.toString())
            var ok = true
            for (s in repository.getStudents()) {
                if (s.name == student.name) {
                    ok = false
                    break
                }
            }
            if (!ok) {
                Toast.makeText(context, "Student already in the list!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
               // repository.addStudent(student.name)
                if (repository.addStudent(etName.text.toString())) {
                    Toast.makeText(context, "Student already in the list!", Toast.LENGTH_SHORT).show()
                } else {
                    studentsAdapter.notifyItemInserted(repository.getStudents().size-1)
                }
                etName.setText("")
                //recyclerView.smoothScrollToPosition(students.size - 1)
            }
        }
        btnRestoreStudent.setOnClickListener {
            if (repository.restoreStudent()) {
                studentsAdapter.notifyItemInserted(repository.getStudents().size)
            }
        }
    }


    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }




}