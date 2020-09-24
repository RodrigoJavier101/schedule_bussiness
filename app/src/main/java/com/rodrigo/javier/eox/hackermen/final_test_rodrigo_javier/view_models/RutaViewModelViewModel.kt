package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Clientes_Entity

class RutaViewModelViewModel :ViewModel(){
    var clientesLista: MutableLiveData<List<Clientes_Entity>>? =null

    fun getClientes(): LiveData<List<Clientes_Entity>> {
        if (clientesLista == null) {
            clientesLista = MutableLiveData<List<Clientes_Entity>>()
            loadClientes()
        }
        return clientesLista as MutableLiveData
    }

    private fun loadClientes() {
        // Do an asyncronous operation to fetch users.
    }
}