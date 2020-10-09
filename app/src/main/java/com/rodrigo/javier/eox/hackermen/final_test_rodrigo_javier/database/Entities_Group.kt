package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database

import androidx.room.Embedded
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


@Entity(tableName = "ruta_table")
data class Ruta_Entity(
    var fecha_ruta: String = "0-0-0",
    @Embedded
    var datos_cliente: Clientes_Entity
) {
    @PrimaryKey(autoGenerate = true)
    var id_ruta_punto_entrega: Int = 0
}


@Entity(tableName = "user_table")
data class User_Entity(
    var user_name: String = "none",
    /*max 4 digitos*/
    var password: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id_user: Int = 0
}

@Entity(tableName = "productos_table")
data class Productos_Entity(

    var nombre_producto: String = "default",
    var precio_producto: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id_producto: Int = 0
}

@Entity(tableName = "ventas_table")
data class Ventas_Entity(
    var valor_producto_vendido: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id_ventas: Int = 0
}

@Entity(tableName = "gastos_table")
data class Gastos_Entity(
    var id_user: Int = 0,
    var fecha_gasto: String = "0-0-0",
    var id_producto: Int = 0,
    var comentario: String = "no-coment"
) {
    @PrimaryKey(autoGenerate = true)
    var id_gastos: Int = 0
}


@Entity(tableName = "proveedores_table")
data class Proveedores_Entity(
    var nombre_proveedor: String = "sin nombre?",
    var domicilio_proveedor: String = "none",
    var telefono_proveedor: Long = 0,
    var mail_proveedor: String = "@agregar",
    var status_proveedor: String = "no-coment",
) {
    @PrimaryKey(autoGenerate = true)
    var id_proveedores: Int = 0
}






