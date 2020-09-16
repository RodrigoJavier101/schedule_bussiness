package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities

import androidx.room.*

//var userSellings = UserSellings()

@Entity(tableName = "user_table")
data class User_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_user: Int,
    var user_name: String,
    var password: Int
)

@Entity(tableName = "productos_table")
data class Productos_Entity(
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
data class Ventas_Entity(
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
data class Gastos_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_gastos: Int,
    var id_user: Int,
    var fecha_gasto: String,
    var id_producto: Int,
)

@Entity(tableName = "clientes_table")
data class Clientes_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_clientes: Int,
    var nombre_cliente: String,
    var mail_cliente: String,
    var telefono_cliente: String,
    var status_cliente: String,
    var domicilio_cliente: String,
)

@Entity(tableName = "proveedores_table")
data class Proveedores_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_proveedores: Int,
    var nombre_proveedor: String,
    var mail_proveedor: String,
    var telefono_proveedor: String,
    var status_proveedor: String,
    var domicilio_proveedor: String,
)

@Entity(tableName = "ruta_table")
data class Ruta_Entity(
    var id_ruta_punto_entrega: Int,
    @Embedded
    @Ignore
    var datos_cliente: Clientes_Entity
)