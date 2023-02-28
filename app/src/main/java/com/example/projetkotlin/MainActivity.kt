package com.example.projetkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewToDo : RecyclerView
    var newArrayList : ArrayList<ToDo> = arrayListOf(
        ToDo(false,"hello"),
        ToDo(false,""),
        ToDo(false,""),
    )
    private val adapter = ToDoAdapter(newArrayList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewToDo =findViewById(R.id.toDoList)
        recyclerViewToDo.adapter = adapter
        recyclerViewToDo.layoutManager = LinearLayoutManager(this)
        recyclerViewToDo.setHasFixedSize(true)


        val addButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addButton.setOnClickListener {
            val toDoAdapter = adapter
            val newItem = ToDo(checked = false, libelle = "")
            toDoAdapter.addItem(newItem)
        }




    }







}