package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "clientes_table")
data class Clientes_Entity(
    var nombre_cliente: String = "no-name",
    var domicilio_cliente: String = "calle",
    var telefono_cliente: Long = 0,
    var mail_cliente: String = "@gregar mail",
    var status_cliente: String = "no coment",
    var id_ruta: Int = 0,
) {
    @PrimaryKey(autoGenerate = true)
    var id_clientes: Int = 0
}

