package com.example.hackeruapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeopleDao {


    @Insert
    fun insertPerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Query("Select * from peopleTable")
    fun getAllPeople(): LiveData<List<Person>>

}
