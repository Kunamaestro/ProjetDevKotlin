package com.example.projetkotlin

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoAdapter(var items: ArrayList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checked: CheckBox = itemView.findViewById(R.id.checkBox)
        var libelle: EditText = itemView.findViewById(R.id.edit_text)
        var deleteButton: ImageButton = itemView.findViewById(R.id.deleteItem)
        var textWatcher: TextWatcher? = null

        init {
            textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Met à jour la valeur de l'élément correspondant dans la liste
                    items[position].libelle = s.toString()
                    Sauvegarde.writeListinPref(context, items)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        context = parent.context
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checked.setOnClickListener{
            if(items[position].checked==true){
                items[position].checked=false
            }else{
                items[position].checked=true
            }
            Sauvegarde.writeListinPref(context,items)
            notifyDataSetChanged()
        }

        holder.libelle.addTextChangedListener(holder.textWatcher)
        holder.libelle.setText(items[position].libelle)

        val currentItem = items[position]
        holder.checked.isChecked = currentItem.checked
        holder.deleteButton.setOnClickListener {
            items.removeAt(position)
            Sauvegarde.writeListinPref(context, items)
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}