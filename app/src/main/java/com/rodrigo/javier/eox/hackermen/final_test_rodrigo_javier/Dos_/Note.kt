package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
class Note(val title: String, val description: String, val priority: Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}