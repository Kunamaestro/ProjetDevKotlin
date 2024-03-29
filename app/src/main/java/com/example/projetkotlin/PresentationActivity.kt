package com.example.projetkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PresentationActivity : AppCompatActivity() {
    private lateinit var items:ArrayList<ToDo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)
        items = ArrayList<ToDo>()
        val button = findViewById<Button>(R.id.discoverApplication)

        button.setOnClickListener{
            val intentToMainActivity : Intent = Intent(this,MainActivity::class.java)
            startActivity(intentToMainActivity)
        }


    }
}