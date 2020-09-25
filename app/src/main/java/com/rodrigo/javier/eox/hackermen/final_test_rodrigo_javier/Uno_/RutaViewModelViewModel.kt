package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RutaViewModelViewModel : ViewModel() {
    lateinit var dao: GestionDao
    var clientesLista: MutableLiveData<List<Clientes_Entity>>? = null

    fun getClientes(): MutableLiveData<List<Clientes_Entity>> {
        if (clientesLista == null) {
            clientesLista = MutableLiveData<List<Clientes_Entity>>()
            loadClientes()
        }
        return clientesLista as MutableLiveData
    }

    private fun loadClientes() {
        // Do an asyncronous operation to fetch clients.
        dao = RoomApplication.gestionDatabase.getGestionDao()
        CoroutineScope(Dispatchers.IO).launch {
            dao.getAllFromClientesTable()
        }

    }
}