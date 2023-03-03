package com.example.projetkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity(){
    lateinit var sharedPreferences:SharedPreferences
    lateinit var recyclerViewToDo : RecyclerView
    lateinit var newArrayList : ArrayList<ToDo>
    lateinit var adapter : ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var nomtache :String
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        recyclerViewToDo =findViewById(R.id.toDoList)
        newArrayList = Sauvegarde.readListFromPref(this) as ArrayList<ToDo>
        adapter = ToDoAdapter(newArrayList)
        recyclerViewToDo.adapter = adapter
        recyclerViewToDo.layoutManager = LinearLayoutManager(this)
        recyclerViewToDo.setHasFixedSize(true)

        println(newArrayList.size)

        val addButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addButton.setOnClickListener {
            val toDoAdapter = adapter
            val builder = AlertDialog.Builder(this)
            val inflater = LayoutInflater.from(this).inflate(R.layout.edit_text_layout,null)
            val editText=inflater.findViewById<EditText>(R.id.et_edittext)

            with(builder){
                setTitle("Entrez la tache")
                setPositiveButton("OK"){dialog,which->
                    nomtache=editText.text.toString()
                    val newItem = ToDo(checked = false, libelle =nomtache )
                    toDoAdapter.addItem(newItem)
                    Sauvegarde.writeListinPref(applicationContext,newArrayList)
                }
                setNegativeButton("Fermer"){dialog,which-> Log.d("Main","Le bouton fermer")}
                setView(inflater)
                show()

            }
        }





    }
    fun loadData(){
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        val gson = Gson()
        val json=sharedPreferences.getString("data_list",null)
        val type: Type = object : TypeToken<ArrayList<ToDo?>?>() {}.type
        newArrayList = gson.fromJson<Any>(json, type) as ArrayList<ToDo>
        if (newArrayList == null) {
            newArrayList = ArrayList()
        }
    }

    fun saveData(){
        val sp :SharedPreferences = applicationContext.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        val gson : Gson = Gson()
        val json : String = gson.toJson(newArrayList)
        editor.putString("data_list",json)
        editor.apply()
        loadData()

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

