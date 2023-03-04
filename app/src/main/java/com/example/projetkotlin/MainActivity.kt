package com.example.projetkotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(){
    lateinit var sharedPreferences:SharedPreferences
    lateinit var recyclerView : RecyclerView
    lateinit var addButton :FloatingActionButton
    lateinit var toDoList : ArrayList<ToDo>
    lateinit var adapter : ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButton = findViewById(R.id.floatingActionButton)
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        recyclerView =findViewById(R.id.toDoList)
        toDoList = Sauvegarde.readListFromPref(this) as ArrayList<ToDo>
        adapter = ToDoAdapter(toDoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        addButton.setOnClickListener{
            toDoList.add(ToDo(false,""))
            Sauvegarde.writeListinPref(this,toDoList)
            adapter.notifyDataSetChanged()
        }

    }

}

