package com.example.projetkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

    class ToDoAdapter(
        var items : List<ToDo>,
    ) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {




        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {

            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
            return ToDoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return items.size
        }
        class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var checked :CheckBox
            var libelle : TextView

            init {
                checked=itemView.findViewById(R.id.checkBox)
                libelle=itemView.findViewById(R.id.edit_text)
            }
        }
    }