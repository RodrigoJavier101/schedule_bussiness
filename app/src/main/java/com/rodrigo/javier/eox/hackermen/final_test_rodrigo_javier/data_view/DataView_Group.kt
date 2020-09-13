package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.data_view



//var userSellings = UserSellings()

data class User_DataView(
    var id_user: Int,
    var user_name: String,
    var password: Int
)

data class Productos_DataView(
    var id_producto: Int = 0,
    var nombre_producto: String = "NONE",
    var precio_producto: Int = 0
)

data class Ventas_DataView(
    var id_ventas: Int,
    var id_user: Int,
    var fecha_venta: String,
    var id_producto: Int,
)

data class Gastos_DataView(
    var id_gastos: Int,
    var id_user: Int,
    var fecha_gasto: String,
    var id_producto: Int,
)

data class Clientes_DataView(
    var id_clientes: Int,
    var nombre_cliente: String,
    var mail_cliente: String,
    var telefono_cliente: String,
    var status_cliente: String,
    var domicilio_cliente: String,
)

data class Proveedores_DataView(
    var id_proveedores: Int,
    var nombre_proveedor: String,
    var mail_proveedor: String,
    var telefono_proveedor: String,
    var status_proveedor: String,
    var domicilio_proveedor: String,
)

data class Agenda_DataView(
    var id_agenda: Int = 0,
    var fecha_programada: String = "none",
    var asunto_agenda: String = "none"
)
