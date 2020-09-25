package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "clientes_table")
data class Clientes_Entity(
    var nombre_cliente: String = "no-name",
    var mail_cliente: String = "@gregar mail",
    var telefono_cliente: Long = 0,
    var status_cliente: String = "no coment",
    var domicilio_cliente: String = "calle",
    var id_ruta: Int = 0,
){
    @PrimaryKey(autoGenerate = true)
    var id_clientes: Int = 0
}


/*
* @Entity(tableName = "note_table")
class Note(val title: String, val description: String, val priority: Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
*
* */

