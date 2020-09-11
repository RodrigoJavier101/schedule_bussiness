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
    fun insertUsers(users: List<User>)

    @Insert
    fun insertProductos(users: List<Productos>)

    @Insert
    fun insertVentas(users: List<Ventas>)

    @Insert
    fun insertGastos(users: List<Gastos>)

    @Insert
    fun insertClientes(users: List<Clientes>)

    @Insert
    fun insertProveedores(users: List<Proveedores>)

    @Insert
    fun insertAgenda(users: List<Agenda>)

    @Update
    fun updateUsers(users: User)

    @Update
    fun updateProductos(users: Productos)

    @Update
    fun updateVentas(users: Ventas)

    @Update
    fun updateGastos(users: Gastos)

    @Update
    fun updateClientes(users: Clientes)

    @Update
    fun updateProveedores(users: Proveedores)

    @Update
    fun updateAgenda(users: Agenda)

    @Delete
    fun deleteUsers(vararg users: User)

    @Delete
    fun deleteProductos(vararg users: Productos)

    @Delete
    fun deleteVentas(vararg users: Ventas)

    @Delete
    fun deleteGastos(vararg users: Gastos)

    @Delete
    fun deleleClientes(vararg users: Clientes)

    @Delete
    fun deleteProveedores(vararg users: Proveedores)

    @Delete
    fun deleteAgenda(vararg users: Agenda)

}