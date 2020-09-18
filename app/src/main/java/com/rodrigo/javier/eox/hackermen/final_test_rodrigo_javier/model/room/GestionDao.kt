package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GestionDao {

    @Query("select * from user_table")
    fun getAllFromUserTable(): LiveData<List<User_Entity>>

    @Query("select * from productos_table")
    fun getAllFromProductosTable(): List<Productos_Entity>

    @Query("select * from ventas_table")
    fun getAllFromVentasTable(): LiveData<List<Ventas_Entity>>

    @Query("select * from gastos_table")
    fun getAllFromGastosTable(): LiveData<List<Gastos_Entity>>

    @Query("select * from clientes_table")
    fun getAllFromClientesTable(): LiveData<List<Clientes_Entity>>

    @Query("select * from proveedores_table")
    fun getAllFromProveedoresTable(): LiveData<List<Proveedores_Entity>>

    @Query("select * from ruta_table")
    fun getAllFromRutaTable(): LiveData<List<Ruta_Entity>>

    @Insert
    fun insertUsers(user: User_Entity)

    @Insert
    fun insertProductos(producto: Productos_Entity)

    @Insert
    fun insertVentas(venta: Ventas_Entity)

    @Insert
    fun insertGastos(gasto: Gastos_Entity)

    @Insert
    fun insertClientes(cliente: Clientes_Entity)

    @Insert
    fun insertProveedores(proveedor: Proveedores_Entity)

    @Insert
    fun insertRuta(ruta: Ruta_Entity)

    @Update
    fun updateUsers(user: User_Entity)

    @Update
    fun updateProductos(producto: Productos_Entity)

    @Update
    fun updateVentas(venta: Ventas_Entity)

    @Update
    fun updateGastos(gasto: Gastos_Entity)

    @Update
    fun updateClientes(cliente: Clientes_Entity)

    @Update
    fun updateProveedores(proveedor: Proveedores_Entity)

    @Update
    fun updateRuta(ruta: Ruta_Entity)

    @Delete
    fun deleteUsers(user: User_Entity)

    @Delete
    fun deleteProductos(productos: Array<out Productos_Entity>)

    @Delete
    fun deleteProducto(producto: Productos_Entity)

    @Delete
    fun deleteVentas(venta: Ventas_Entity)

    @Delete
    fun deleteGastos(gasto: Gastos_Entity)

    @Delete
    fun deleleClientes(cliente: Clientes_Entity)

    @Delete
    fun deleteProveedores(proveedor: Proveedores_Entity)

    @Delete
    fun deleteRuta(ruta: Ruta_Entity)

}