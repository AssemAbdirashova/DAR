package com.example.tictac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter (): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var list = listOf<User>()

    fun submitList(newList: List<User>?) {
        list = newList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

       private val tvName = view.findViewById<TextView>(R.id.tv_name)
       private val tvLose = view.findViewById<TextView>(R.id.tv_lose_count)
       private val tvWin = view.findViewById<TextView>(R.id.tv_win_count)

        fun bind(position: Int, item: User) {
         tvName.text = "User: ${item.id} - ${item.name}"
         tvLose.text = item.loseCount.toString()
         tvWin.text = item.winCount.toString()
        }
    }

}