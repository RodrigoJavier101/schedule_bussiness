package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class GestionRepository(application: Application?) {
    private val dao: GestionDao
    val allClientes_: LiveData<List<Clientes_Entity>?>?
    val allUsers_: LiveData<List<User_Entity>?>?
    val allProveedores_: LiveData<List<Proveedores_Entity>?>?
    val allProductos_: LiveData<List<Productos_Entity>?>?
    val allRuta_: LiveData<List<Ruta_Entity>?>?
    val allGastos_: LiveData<List<Gastos_Entity>?>?
    val allVentas_: LiveData<List<Ventas_Entity>?>?

    /*Applo es una clase de contexto para crear la ddbb en el contexto*/
    init {
        val database = GestionDatabase.getInstance(application!!)
        dao = database!!.setDao()
        allClientes_ = dao.getAllClientes()
        allUsers_ = dao.getAllUsers()
        allProveedores_ = dao.getAllProveedores()
        allProductos_ = dao.getAllProductos()
        allRuta_ = dao.getAllRuta()
        allGastos_ = dao.getAllGastos()
        allVentas_ = dao.getAllVentas()
    }

    /*Seccion Cliente*********************************************************************/
    fun insertCliente(note: Clientes_Entity?) {
        InsertClienteAsyncTask(dao).execute(note)
    }

    fun updateCliente(note: Clientes_Entity?) {
        UpdateClienteAsyncTask(dao).execute(note)
    }

    //    hay que crear un asynctask para cada metodo
    fun deleteCliente(note: Clientes_Entity?) {
        DeleteClienteAsyncTask(dao).execute(note)
    }

    fun deleteAllClientes() {
        DeleteAllNotesAsyncTask(dao).execute()
    }

    /*no consrva el puntero a la misma clase y se evitan memory leaks*/ /*se le debn pasar los tres tipos al AsycTask solo notes por eso se le pone Void*/
    private class InsertClienteAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Clientes_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Clientes_Entity?): Void? {
            dao.insertCliente(notes[0])
            return null
        }
    }

    private class UpdateClienteAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Clientes_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg notes: Clientes_Entity?): Void? {
            dao.updateCliente(notes[0])
            return null
        }
    }

    private class DeleteClienteAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Clientes_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg notes: Clientes_Entity?): Void? {
            dao.deleteCliente(notes[0])
            return null
        }
    }

    private class DeleteAllNotesAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            dao.deleteAllClientes()
            return null
        }

    }

    /*Seccion User****************************************************************************************/
    fun insertUser(user: User_Entity?) {
        InsertUserAsyncTask(dao).execute(user)
    }

    fun updateUser(user: User_Entity?) {
        UpdateUserAsyncTask(dao).execute(user)
    }

    fun deleteUser(user: User_Entity?) {
        DeleteUserAsyncTask(dao).execute(user)
    }

    fun deleteAllUsers() {
        DeleteAllUsersAsyncTask(dao).execute()
    }

   /* fun getPassword(): Int? {
        GetPasswordFromUserAsyncTask(dao).execute()
    }*/

    private class InsertUserAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<User_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg user: User_Entity?): Void? {
            dao.insertUser(user[0])
            return null
        }
    }

    private class UpdateUserAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<User_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg user: User_Entity?): Void? {
            dao.updateUser(user[0])
            return null
        }
    }

    private class DeleteUserAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<User_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg user: User_Entity?): Void? {
            dao.deleteUser(user[0])
            return null
        }
    }

    private class DeleteAllUsersAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            dao.deleteAllUsers()
            return null
        }

    }

   /* private class GetPasswordFromUserAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<String?, Void?, Void?>() {
        override fun doInBackground(vararg nombre: String?): LiveData<Int>? {
            dao.getPasswordFromUserTable(nombre)
            return null
        }

    }*/

    /*Seccion Proveedores******************************************************************/
    fun insertProveedor(proveedor: Proveedores_Entity?) {
        InsertProveedorAsyncTask(dao).execute(proveedor)
    }

    fun updateProveedor(proveedor: Proveedores_Entity?) {
        UpdateProveedorAsyncTask(dao).execute(proveedor)
    }

    fun deleteProveedor(proveedor: Proveedores_Entity?) {
        DeleteProveedorAsyncTask(dao).execute(proveedor)
    }

    fun deleteAllProveedores() {
        DeleteAllUsersAsyncTask(dao).execute()
    }

    private class InsertProveedorAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Proveedores_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg proveeodor: Proveedores_Entity?): Void? {
            dao.insertProveedor(proveeodor[0])
            return null
        }
    }

    private class UpdateProveedorAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Proveedores_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg proveedor: Proveedores_Entity?): Void? {
            dao.updateProveedor(proveedor[0])
            return null
        }
    }

    private class DeleteProveedorAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Proveedores_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg proveedor: Proveedores_Entity?): Void? {
            dao.deleteProveedor(proveedor[0])
            return null
        }
    }

    private class DeleteAllProveedoresAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            dao.deleteAllProveedores()
            return null
        }

    }

    /*Seccion Productos*******************************************************************/
    fun insertProducto(producto: Productos_Entity?) {
        InsertProductoAsyncTask(dao).execute(producto)
    }

    fun updateProducto(producto: Productos_Entity?) {
        UpdateProductoAsyncTask(dao).execute(producto)
    }

    fun deleteProducto(producto: Productos_Entity?) {
        DeleteProductoAsyncTask(dao).execute(producto)
    }

    fun deleteAllProductos() {
        DeleteAllProductosAsyncTask(dao).execute()
    }

    private class InsertProductoAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Productos_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg producto: Productos_Entity?): Void? {
            dao.insertProducto(producto[0])
            return null
        }
    }

    private class UpdateProductoAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Productos_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg producto: Productos_Entity?): Void? {
            dao.updateProducto(producto[0])
            return null
        }
    }

    private class DeleteProductoAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Productos_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg producto: Productos_Entity?): Void? {
            dao.deleteProducto(producto[0])
            return null
        }
    }

    private class DeleteAllProductosAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            dao.deleteAllProductos()
            return null
        }

    }

    /*Seccion Ruta ************************************************************************************/
    fun insertRuta(ruta: Ruta_Entity?) {
        InsertRutaAsyncTask(dao).execute(ruta)
    }

    fun updateRuta(ruta: Ruta_Entity?) {
        UpdateRutaAsyncTask(dao).execute(ruta)
    }

    fun deleteRuta(ruta: Ruta_Entity?) {
        DeleteRutaAsyncTask(dao).execute(ruta)
    }

    fun deleteAllRuta() {
        DeleteAllRutaAsyncTask(dao).execute()
    }

    private class InsertRutaAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Ruta_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg ruta: Ruta_Entity?): Void? {
            dao.insertRuta(ruta[0])
            return null
        }
    }

    private class UpdateRutaAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Ruta_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg ruta: Ruta_Entity?): Void? {
            dao.updateRuta(ruta[0])
            return null
        }
    }

    private class DeleteRutaAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Ruta_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg ruta: Ruta_Entity?): Void? {
            dao.deleteRuta(ruta[0])
            return null
        }
    }

    private class DeleteAllRutaAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            dao.deleteAllRuta()
            return null
        }

    }

    /*Seccion Gastos**********************************************************************/
    fun insertGasto(gasto: Gastos_Entity?) {
        InsertGastoAsyncTask(dao).execute(gasto)
    }

    fun updateGasto(gasto: Gastos_Entity?) {
        UpdateGastoAsyncTask(dao).execute(gasto)
    }

    fun deleteGasto(gasto: Gastos_Entity?) {
        DeleteGastoAsyncTask(dao).execute(gasto)
    }

    fun deleteAllGastos() {
        DeleteAllGastosAsyncTask(dao).execute()
    }

    private class InsertGastoAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Gastos_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg gasto: Gastos_Entity?): Void? {
            dao.insertGasto(gasto[0])
            return null
        }
    }

    private class UpdateGastoAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Gastos_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg gasto: Gastos_Entity?): Void? {
            dao.updateGasto(gasto[0])
            return null
        }
    }

    private class DeleteGastoAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Gastos_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg gasto: Gastos_Entity?): Void? {
            dao.deleteGasto(gasto[0])
            return null
        }
    }

    private class DeleteAllGastosAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            dao.deleteAllGastos()
            return null
        }

    }

    /*Seccion Ventas*************************************************************************/
    fun insertVenta(venta: Ventas_Entity?) {
        InsertVentaAsyncTask(dao).execute(venta)
    }

    fun updateVenta(venta: Ventas_Entity?) {
        UpdateVentaAsyncTask(dao).execute(venta)
    }

    fun deleteVenta(venta: Ventas_Entity?) {
        DeleteVentaAsyncTask(dao).execute(venta)
    }

    fun deleteAllVentas() {
        DeleteAllVentasAsyncTask(dao).execute()
    }

    private class InsertVentaAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Ventas_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg venta: Ventas_Entity?): Void? {
            dao.insertVenta(venta[0])
            return null
        }
    }

    private class UpdateVentaAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Ventas_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg venta: Ventas_Entity?): Void? {
            dao.updateVenta(venta[0])
            return null
        }
    }

    private class DeleteVentaAsyncTask(
        private val dao: GestionDao
    ) : AsyncTask<Ventas_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg venta: Ventas_Entity?): Void? {
            dao.deleteVenta(venta[0])
            return null
        }
    }

    private class DeleteAllVentasAsyncTask internal constructor(
        private val dao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            dao.deleteAllVentas()
            return null
        }

    }


}