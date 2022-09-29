package com.example.hackeruapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.hackeruapp.ui.NotesActivity

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_name)
    }

    fun onStartClick(view:View) {
        val intent = Intent(this, NotesActivity::class.java)
        val name = findViewById<EditText>(R.id.name_et).text.toString()
        intent.putExtra("extra",name)
        startActivity(intent)
    }
}