package com.example.projetkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoAdapter(
        val items : ArrayList<ToDo>,
    ) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {



        class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var checked :CheckBox =itemView.findViewById(R.id.checkBox)
            var libelle : EditText = itemView.findViewById(R.id.edit_text)
            var deleteButton : ImageButton =itemView.findViewById(R.id.deleteItem)


        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
            return ToDoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
            val currentItem = items[position]
            holder.checked.isChecked = currentItem.checked
            holder.libelle.setText(currentItem.libelle)
            holder.deleteButton.setOnClickListener{
                val position = holder.adapterPosition
                items.removeAt(position)
                notifyDataSetChanged()
            }


        }

        override fun getItemCount(): Int {
            return items.size
        }

    fun addItem(newItem: ToDo) {
        items.add(newItem)
        notifyDataSetChanged()
    }




    }