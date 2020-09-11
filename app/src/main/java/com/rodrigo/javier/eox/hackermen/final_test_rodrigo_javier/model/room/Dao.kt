package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import androidx.room.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities.*

@Dao
interface GestionDao {

    @Query("select * from user_table")
    fun getAllFromUserTable(): List<User>

    @Query("select * from productos_table")
    fun getAllFromProductosTable(): List<Productos>

    @Query("select * from ventas_table")
    fun getAllFromVentasTable(): List<Ventas>

    @Query("select * from gastos_table")
    fun getAllFromGastosTable(): List<Gastos>

    @Query("select * from clientes_table")
    fun getAllFromClientesTable(): List<Clientes>

    @Query("select * from proveedores_table")
    fun getAllFromProveedoresTable(): List<Proveedores>

    @Query("select * from agenda_table")
    fun getAllFromAgendaTable(): List<Agenda>

    @Insert
    fun insertUsers(user: User)

    @Insert
    fun insertProductos(producto: Productos)

    @Insert
    fun insertVentas(venta: Ventas)

    @Insert
    fun insertGastos(gasto: Gastos)

    @Insert
    fun insertClientes(cliente: Clientes)

    @Insert
    fun insertProveedores(proveedor: Proveedores)

    @Insert
    fun insertAgenda(agenda: Agenda)

    @Update
    fun updateUsers(user: User)

    @Update
    fun updateProductos(producto: Productos)

    @Update
    fun updateVentas(venta: Ventas)

    @Update
    fun updateGastos(gasto: Gastos)

    @Update
    fun updateClientes(cliente: Clientes)

    @Update
    fun updateProveedores(proveedor: Proveedores)

    @Update
    fun updateAgenda(agenda: Agenda)

    @Delete
    fun deleteUsers(user: User)

    @Delete
    fun deleteProductos(producto: Productos)

    @Delete
    fun deleteVentas(venta: Ventas)

    @Delete
    fun deleteGastos(gasto: Gastos)

    @Delete
    fun deleleClientes(cliente: Clientes)

    @Delete
    fun deleteProveedores(proveedor: Proveedores)

    @Delete
    fun deleteAgenda(agenda: Agenda)

}