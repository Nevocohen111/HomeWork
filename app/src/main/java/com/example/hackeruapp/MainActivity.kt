package com.example.hackeruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    lateinit var repository: Repository
    private var adapter = RecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = Repository(this)
        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        val button = findViewById<Button>(R.id.add_button)
        val input = findViewById<EditText>(R.id.item_name_input)
        button.setOnClickListener {
            val radioGroup = findViewById<RadioGroup>(R.id.radioItemSelect)
            val checkedId = radioGroup.checkedRadioButtonId
            adapter.notifyDataSetChanged()

            if (!isInputValid())
                Toast.makeText(this, "Please enter a valid Input", Toast.LENGTH_SHORT).show()
            else {
                if (checkedId == -1)
                    Toast.makeText(this, "Please select an image!", Toast.LENGTH_SHORT).show()

                when (checkedId) {
                    R.id.radioButton1 ->
                        thread(start = true) {
                            repository.addPerson(Person(input.text.toString(), R.drawable.avatar1_foreground))
                        }
                    R.id.radioButton2 ->
                        thread(start = true) {
                            repository.addPerson(Person(input.text.toString(), R.drawable.avatar2_foreground))
                        }
                    R.id.radioButton3 ->
                        thread(start = true) {
                            repository.addPerson(Person(input.text.toString(), R.drawable.avatar3_foreground))
                        }
                }
            }
        }
        createRecyclerView()
    }

    private fun isInputValid(): Boolean {
        val input = findViewById<EditText>(R.id.item_name_input)
        if (input.text.contains("[0-9]+?-?|/D[a-zA-Z]{3,}$[0-9]".toRegex()) || input.text.isNullOrEmpty()) {
            return false
        }
        return true
    }

    private fun createRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        val peopleListLiveData = repository.getAllPeopleAsLiveData()
        peopleListLiveData.observe(this) { personList ->
            adapter.viewUpdater(personList)
        }
    }
}






