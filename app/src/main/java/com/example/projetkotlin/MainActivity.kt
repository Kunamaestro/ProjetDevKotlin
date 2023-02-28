package com.example.projetkotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences:SharedPreferences
    lateinit var recyclerViewToDo : RecyclerView
    var newArrayList : ArrayList<ToDo> = ArrayList<ToDo>()
    private val adapter = ToDoAdapter(newArrayList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        loadData()

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

    fun loadData(){
        val sp :SharedPreferences = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        val gson : Gson = Gson()


    }

    fun saveData(){
        val sp :SharedPreferences = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        val gson : Gson = Gson()
        val json : String = gson.toJson(newArrayList)

        editor.apply()


    }


    override fun onPause() {
        super.onPause()
        saveData()
    }
    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }



}

