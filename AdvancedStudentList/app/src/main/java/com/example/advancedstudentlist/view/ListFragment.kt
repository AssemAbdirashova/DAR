package com.example.advancedstudentlist.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedstudentlist.R
import com.example.advancedstudentlist.StudentListAdapter
import com.example.advancedstudentlist.models.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListFragment : Fragment() {

    private val KEY_RECYCLER_STATE = "recycler_state"
    lateinit var navController: NavController
    private lateinit var listener: StudentListAdapter.OnItemClickListener
    private var scrollPosition = 0
    private lateinit var recyclerView: RecyclerView
    private val students = mutableSetOf<Student>()
    private var restore: Queue<Student> = LinkedList<Student>()
    private val st = Stack<Student>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etName = view.findViewById<EditText>(R.id.et_name)
        val btnAddStudent = view.findViewById<Button>(R.id.btn_add_student)
        val btnRestoreStudent = view.findViewById<FloatingActionButton>(R.id.btn_restore_student)
        recyclerView = view.findViewById(R.id.student_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        navController = Navigation.findNavController(view)


        listener = object : StudentListAdapter.OnItemClickListener {
            override fun onStudentSelected(student: Student, position: Int) {
                val action = ListFragmentDirections.listToDetail(student)
                view.findNavController().navigate(action)
                hideKeyboard()
                scrollPosition = position
            }
            override fun onStudentDeleted(restStudent: Student, position: Int) {
                when {
                    restore.size < 5 || restore.isEmpty() -> {
                        restore.add(restStudent)
                    }
                    else -> {
                        restore.remove()
                        restore.add(restStudent)
                    }
                }
                students.remove(restStudent)
            }
        }
        val studentsAdapter = StudentListAdapter(students.toMutableList(), listener)
        recyclerView.adapter = studentsAdapter

        btnAddStudent.setOnClickListener {
            val student = Student(name = etName.text.toString())
            var ok = true
            for (s in students) {
                if (s.name == student.name) {
                    ok = false
                    break
                }
            }
            if (!ok) {
                Toast.makeText(context, "Student already in the list!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                students.add(student)
                studentsAdapter.addStudent(student)
                etName.setText("")
                recyclerView.smoothScrollToPosition(students.size - 1);
            }
        }
        btnRestoreStudent.setOnClickListener {
            while (!restore.isEmpty()) {
                st.push(restore.peek())
                restore.remove()
            }
            if(!st.isEmpty()) {
                recyclerView.smoothScrollToPosition(students.size - 1);
                studentsAdapter.addStudent(st.peek())
                st.pop()
            }
            else {
                Toast.makeText(context, "Trash is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onPause() {
        super.onPause()
        mBundleRecyclerViewState = Bundle()
        val listState = recyclerView!!.layoutManager!!.onSaveInstanceState()
        mBundleRecyclerViewState!!.putParcelable(KEY_RECYCLER_STATE, listState)
    }

    override fun onResume() {
        super.onResume()
        if (mBundleRecyclerViewState != null) {
            val listState = mBundleRecyclerViewState!!.getParcelable<Parcelable>(KEY_RECYCLER_STATE)
            recyclerView!!.layoutManager!!.onRestoreInstanceState(listState)
        }
    }

    companion object {
        private var mBundleRecyclerViewState: Bundle? = null
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }


}