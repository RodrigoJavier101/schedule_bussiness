package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//var userSellings = UserSellings()

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id_user: Int,
    var user_name: String,
    var password: Int
)

@Entity(tableName = "productos_table")
data class Productos(
    @PrimaryKey(autoGenerate = true)
    var id_producto: Int,
    var nombre_producto: String,
    var precio_producto: Int
)

//@Entity(
//    tableName = "ventas_table",
//    foreignKeys = arrayOf(
//        ForeignKey(
//            entity = User::class,
//            parentColumns = arrayOf("id_user"),
//            childColumns = arrayOf("id_user"),
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = Productos::class,
//            parentColumns = arrayOf("id_producto"),
//            childColumns = arrayOf("id_producto"),
//            onDelete = ForeignKey.CASCADE
//        )
//    )
//)
@Entity(tableName = "ventas_table")
data class Ventas(
    @PrimaryKey(autoGenerate = true)
    var id_ventas: Int,
    var id_user: Int,
    var fecha_venta: String,
    var id_producto: Int,
)

//@Entity(
//    tableName = "gastos_table",
//    foreignKeys = arrayOf(
//        ForeignKey(
//            entity = User::class,
//            parentColumns = arrayOf("id_user"),
//            childColumns = arrayOf("id_user"),
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = Productos::class,
//            parentColumns = arrayOf("id_producto"),
//            childColumns = arrayOf("id_producto"),
//            onDelete = ForeignKey.CASCADE
//        )
//    )
//)
@Entity(tableName = "gastos_table")
data class Gastos(
    @PrimaryKey(autoGenerate = true)
    var id_gastos: Int,
    var id_user: Int,
    var fecha_gasto: String,
    var id_producto: Int,
)

@Entity(tableName = "clientes_table")
data class Clientes(
    @PrimaryKey(autoGenerate = true)
    var id_clientes: Int,
    var nombre_cliente: String,
    var mail_cliente: String,
    var telefono_cliente: String,
    var status_cliente: String,
    var domicilio_cliente: String,
)

@Entity(tableName = "proveedores_table")
data class Proveedores(
    @PrimaryKey(autoGenerate = true)
    var id_proveedores: Int,
    var nombre_proveedor: String,
    var mail_proveedor: String,
    var telefono_proveedor: String,
    var status_proveedor: String,
    var domicilio_proveedor: String,
)

@Entity(tableName = "agenda_table")
data class Agenda(
    @PrimaryKey(autoGenerate = true)
    var id_agenda: Int,
    var fecha_programada: String,
    var asunto_agenda: String
)