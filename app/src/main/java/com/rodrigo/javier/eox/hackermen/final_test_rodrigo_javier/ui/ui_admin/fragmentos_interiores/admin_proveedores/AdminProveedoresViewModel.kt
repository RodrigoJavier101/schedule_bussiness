package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_proveedores

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionRepository
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Proveedores_Entity

class AdminProveedoresViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GestionRepository
    val allProveedores: LiveData<List<Proveedores_Entity>?>?

    init {
        repository = GestionRepository(application)
        allProveedores = repository.allProveedores_
    }

    fun insertProveedor(proveedor: Proveedores_Entity?) {
        repository.insertProveedor(proveedor)
    }

    fun updateProveedor(proveedor: Proveedores_Entity?) {
        repository.updateProveedor(proveedor)
    }

    fun deleteProveedor(proveedor: Proveedores_Entity?) {
        repository.deleteProveedor(proveedor)
    }

    fun deleteAllProveedores() {
        repository.deleteAllProveedores()
    }

}