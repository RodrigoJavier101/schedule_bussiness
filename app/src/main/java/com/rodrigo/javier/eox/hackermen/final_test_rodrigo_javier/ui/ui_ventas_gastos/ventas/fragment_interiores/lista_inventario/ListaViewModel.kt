package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.ventas.fragment_interiores.lista_inventario

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionRepository
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity

class ListaViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GestionRepository
    val allProducts: LiveData<List<Productos_Entity>?>?
    private val selected = MutableLiveData<Productos_Entity>()

    init {
        repository = GestionRepository(application)
        allProducts = repository.allProductos_
    }

    fun setProductSelected(item: Productos_Entity) {
        selected.value = item
    }

    fun getProductSelected(): MutableLiveData<Productos_Entity>? {
        return selected
    }


    fun insertProduct(producto: Productos_Entity?) {
        repository.insertProducto(producto)
    }

    fun updateProduct(producto: Productos_Entity?) {
        repository.updateProducto(producto)
    }

    fun deleteProduct(producto: Productos_Entity?) {
        repository.deleteProducto(producto)
    }

    fun deleteAllProducts() {
        repository.deleteAllProductos()
    }

}