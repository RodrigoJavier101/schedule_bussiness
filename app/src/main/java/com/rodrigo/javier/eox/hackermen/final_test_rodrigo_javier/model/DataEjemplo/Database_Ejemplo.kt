package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.DataEjemplo


//var userSellings = UserSellings()


data class User(
    var id: Int,
    var user_name: String,
    var password: Int
)

data class Productos(
    var id: Int,
    var nombre_producto: String,
    var precio_producto: Int
)

data class Ventas(
    var id: Int,
    var id_user: Int,
    var fecha_venta: String,
    var id_item: Int,
)

data class Gastos(
    var id: Int,
    var id_user: Int,
    var fecha_gasto: String,
    var id_item: Int,
)

data class Clientes(
    var id: Int,
    var nombre_cliente: String,
    var mail_cliente: String,
    var telefono_cliente: String,
    var status_cliente: String,
    var domicilio_cliente: String,
)

data class Proveedores(
    var id: Int,
    var nombre_proveedor: String,
    var mail_proveedor: String,
    var telefono_proveedor: String,
    var status_proveedor: String,
    var domicilio_proveedor: String,
)
