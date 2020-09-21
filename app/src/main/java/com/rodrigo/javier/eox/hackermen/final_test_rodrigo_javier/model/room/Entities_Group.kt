package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import androidx.room.*


@Entity(tableName = "ruta_table")
data class Ruta_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_ruta_punto_entrega: Int,
    var fecha_ruta:String="0-0-0",
    @Embedded
    var datos_cliente: Clientes_Entity
)


@Entity(tableName = "user_table")
data class User_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_user: Int = 0,
    var user_name: String = "none",
    /*max 4 digitos*/
    var password: Int = 9999
)

@Entity(tableName = "productos_table")
data class Productos_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_producto: Int = 0,
    var nombre_producto: String = "default",
    var precio_producto: Int = 0
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
    var id_ventas: Int = 0,
    var id_user: Int = 0,
    var fecha_venta: String = "0-0-0",
    var id_producto: Int = 0,
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
    var id_gastos: Int = 0,
    var id_user: Int = 0,
    var fecha_gasto: String = "0-0-0",
    var id_producto: Int = 0,
    var comentario: String = "no-coment"

)

@Entity(tableName = "clientes_table")
data class Clientes_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_clientes: Int = 0,
    var nombre_cliente: String = "no-name",
    var mail_cliente: String = "@gregar mail",
    var telefono_cliente: Long = 0,
    var status_cliente: String = "no coment",
    var domicilio_cliente: String = "calle",
    var id_ruta: Int = 0,
)

@Entity(tableName = "proveedores_table")
data class Proveedores_Entity(
    @PrimaryKey(autoGenerate = true)
    var id_proveedores: Int = 0,
    var nombre_proveedor: String = "sin nombre?",
    var mail_proveedor: String = "@agregar",
    var telefono_proveedor: String = "sin-numero",
    var status_proveedor: String = "no-coment",
    var domicilio_proveedor: String = "none",
)

