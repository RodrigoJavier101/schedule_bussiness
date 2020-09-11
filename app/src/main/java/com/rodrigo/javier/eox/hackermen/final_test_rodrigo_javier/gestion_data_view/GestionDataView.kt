package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.gestion_data_view

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//var userSellings = UserSellings()

data class User(
    var id_user: Int,
    var user_name: String,
    var password: Int
)

data class Productos(
    var id_producto: Int,
    var nombre_producto: String,
    var precio_producto: Int
)


data class Ventas(
    var id_ventas: Int,
    var id_user: Int,
    var fecha_venta: String,
    var id_producto: Int,
)

data class Gastos(
    var id_gastos: Int,
    var id_user: Int,
    var fecha_gasto: String,
    var id_producto: Int,
)

data class Clientes(
    var id_clientes: Int,
    var nombre_cliente: String,
    var mail_cliente: String,
    var telefono_cliente: String,
    var status_cliente: String,
    var domicilio_cliente: String,
)

data class Proveedores(
    var id_proveedores: Int,
    var nombre_proveedor: String,
    var mail_proveedor: String,
    var telefono_proveedor: String,
    var status_proveedor: String,
    var domicilio_proveedor: String,
)

data class Agenda(
    var id_agenda: Int,
    var fecha_programada: String,
    var asunto_agenda: String
)