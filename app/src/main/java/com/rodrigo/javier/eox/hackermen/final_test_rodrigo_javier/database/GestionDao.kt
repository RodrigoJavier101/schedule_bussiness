package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GestionDao {
    /*SECCION CLIENTES TABLE*/
    @Insert
    fun insertCliente(cliente: Clientes_Entity?)

    @Update
    fun updateCliente(cliente: Clientes_Entity?)

    /*EJEMPLO DE CÓDIGO
    *  @Update(onConflict = REPLACE)
    fun updateCliente(cliente: Clientes_Entity)
*/

    @Delete
    fun deleteCliente(cliente: Clientes_Entity?)

    @Query("delete from clientes_table")
    fun deleteAllClientes()

    @Query("select * from clientes_table order by nombre_cliente desc")
    fun getAllClientes(): LiveData<List<Clientes_Entity>?>?


    /*SECCION USER*/
    @Query("select * from user_table")
    fun getAllUsers(): LiveData<List<User_Entity>?>?

    @Query("select * from user_table")
    fun getAllUsers_2(): List<User_Entity>

    @Query("delete from user_table")
    fun deleteAllUsers()

    @Query("select password from user_table where user_name = :userName")
    fun getPasswordFromUserTable(userName: String?): Int?

    @Insert
    fun insertUser(user: User_Entity?)

    @Update
    fun updateUser(user: User_Entity?)

    @Delete
    fun deleteUser(user: User_Entity?)


    /*SECCION PROVEEDOR*/
    @Insert
    fun insertProveedor(proveedor: Proveedores_Entity?)

    @Query("delete from proveedores_table")
    fun deleteAllProveedores()

    @Update
    fun updateProveedor(proveedor: Proveedores_Entity?)

    @Delete
    fun deleteProveedor(proveedor: Proveedores_Entity?)

    @Query("select * from proveedores_table")
    fun getAllProveedores(): LiveData<List<Proveedores_Entity>?>?


    /*SECCION PRODUCTOS*/
    @Query("select * from productos_table")
    fun getAllProductos(): LiveData<List<Productos_Entity>?>?

    @Query("select * from productos_table")
    fun getAllProductos_2(): List<Productos_Entity>

    @Query("delete from productos_table")
    fun deleteAllProductos()

    @Insert
    fun insertProducto(producto: Productos_Entity?)

    @Update
    fun updateProducto(producto: Productos_Entity?)

    @Delete
    fun deleteProductos(productos: Array<out Productos_Entity>)

    @Delete
    fun deleteProducto(producto: Productos_Entity?)


    /*SECCION RUTA*/
    @Delete
    fun deleteRuta(ruta: Ruta_Entity?)

    @Query("delete from ruta_table")
    fun deleteAllRuta()

    @Update
    fun updateRuta(ruta: Ruta_Entity?)

    @Insert
    fun insertRuta(ruta: Ruta_Entity?)

    @Query("select * from ruta_table")
    fun getAllRuta(): LiveData<List<Ruta_Entity>?>?



    /*SECCION GASTOS*/
    @Delete
    fun deleteGasto(gasto: Gastos_Entity?)

    @Query("delete from gastos_table")
    fun deleteAllGastos()

    @Update
    fun updateGasto(gasto: Gastos_Entity?)

    @Insert
    fun insertGasto(gasto: Gastos_Entity?)

    @Query("select * from gastos_table")
    fun getAllGastos(): LiveData<List<Gastos_Entity>?>?



    /*SECCION VENTAS*/
    @Delete
    fun deleteVenta(venta: Ventas_Entity?)

    @Query("delete from ventas_table")
    fun deleteAllVentas()

    @Update
    fun updateVenta(venta: Ventas_Entity?)

    @Insert
    fun insertVenta(venta: Ventas_Entity?)

    @Query("select * from ventas_table")
    fun getAllVentas(): LiveData<List<Ventas_Entity>?>?


}