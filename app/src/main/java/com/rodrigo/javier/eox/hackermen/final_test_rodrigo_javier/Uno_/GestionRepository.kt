package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GestionRepository {

    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    val allClientes: LiveData<MutableList<Clientes_Entity>> = dao.getAllFromClientesTable()

    fun getAllProductosAsyncTask() {
        AllClientesAsyncTask(dao)
    }

    private class AllClientesAsyncTask internal constructor(
        private val dao:
        GestionDao
    ) :
        AsyncTask<Clientes_Entity, Void, Void>() {
        override fun doInBackground(vararg p0: Clientes_Entity?): Void? {
            dao.getAllFromClientesTable()
            return null
        }
    }

    sealed class Result<out R> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Error(val exception: Exception) : Result<Nothing>()
    }

    suspend fun downloadFromNetwork(url: String) =
        withContext(Dispatchers.IO) {
            try {

            } catch (e: Exception) {

            }
        }


    /* private class InsertAsyncTask internal constructor(
      private val dao:
      GestionDao
  ) :
      AsyncTask<Productos_Entity, Void, Void>() {
      override fun doInBackground(vararg params: Productos_Entity): Void? {
          dao.insertProductos(params[0])
          return null
      }
  }*/

    /*  private class DeleteAsyncTask internal constructor(
          private val dao:
          GestionDao
      ) :
          AsyncTask<Productos_Entity, Void, Void>() {
          override fun doInBackground(vararg params: Productos_Entity): Void? {
              dao.deleteProductos(params)
              return null
          }
    }*/

    /*fun insertProducto(producto: Productos_Entity) {
        InsertAsyncTask(dao).execute(producto)
    }*/

    /*  fun deleteAllVentas() {
          val lista_productos: Array<Productos_Entity>? = allProductos.value?.toTypedArray()

          if (lista_productos != null) {
              DeleteAsyncTask(dao).execute(*lista_productos)
          }
      }*/
}