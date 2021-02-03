package com.example.advancedstudentlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedstudentlist.models.Student

class StudentListAdapter(private val itemClickListener: OnItemClickListener? = null): RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    //private var list = listOf<Student>()
    private var list = listOf<Student>()

    fun submitList(newList: List<Student>?) {
        list = newList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            //val action = ListFragmentDirections.listToDetail(list[position])
            //it.findNavController().navigate(action)
            itemClickListener?.onStudentSelected(list[position], position)
        }
        holder.btnDelete.setOnClickListener {
            itemClickListener?.
            onStudentDeleted(list[position], position, this)
        }

    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tv_id)
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val btnDelete: Button = view.findViewById(R.id.delete_item)
        fun bind(item: Student){
            tvId.text = item.id.toString()
            tvName.text = item.name
        }
    }

    interface OnItemClickListener {
        fun onStudentSelected(student: Student, position: Int)
        fun onStudentDeleted(restStudent: Student,position: Int, adapter: StudentListAdapter)
    }
}