package com.example.projetkotlin

import android.content.Context
import com.google.gson.Gson
import android.preference.PreferenceManager
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

object Sauvegarde {
    private const val LIST_KEY = "list_key"
    fun writeListinPref(context: Context?, list: ArrayList<ToDo>) {
        val gson = Gson()
        val jsonString = gson.toJson(list)
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.putString(LIST_KEY, jsonString)
        editor.apply()
    }

    fun readListFromPref(context: Context?): ArrayList<ToDo> {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString = pref.getString(LIST_KEY, "")
        val gson = Gson()
        val type = object : TypeToken<ArrayList<ToDo?>?>() {}.type
        val list = gson.fromJson<ArrayList<ToDo>>(jsonString, type)
        val list2: ArrayList<ToDo> = ArrayList()
        return list ?: list2
    }


}