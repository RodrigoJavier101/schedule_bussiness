package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_clientes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionRepository

class AdminClientesViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GestionRepository
    val allClientes: LiveData<List<Clientes_Entity>?>?

    init {
        repository = GestionRepository(application)
        allClientes = repository.allClientes_
    }

    fun insertCliente(cliente: Clientes_Entity?) {
        repository.insertCliente(cliente)
    }

    fun updateCliente(cliente: Clientes_Entity?) {
        repository.updateCliente(cliente)
    }

    fun deleteCliente(cliente: Clientes_Entity?) {
        repository.deleteCliente(cliente)
    }

    fun deleteAllClientes() {
        repository.deleteAllClientes()
    }

}