package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionRepository


/*subclass de view model, éste permite pasarle applciation enel constructor por lo  que no se
necesita context ???, si se guarda el context en la act habran memory leaks
se deberia pasar el */
class RutaViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GestionRepository
    val allClientes: LiveData<List<Clientes_Entity>?>?

    init {
        repository = GestionRepository(application)
        allClientes = repository.allClientes_
    }
    /*la activity solamente tiene una referencia al viewmodel por lo que se necesita nuevamente
    mets para traer datos desde repository*/
    fun insert(cliente: Clientes_Entity?) {
        repository.insertCliente(cliente)
    }

    fun update(cliente: Clientes_Entity?) {
        repository.updateCliente(cliente)
    }

    fun delete(cliente: Clientes_Entity?) {
        repository.deleteCliente(cliente)
    }

    fun deleteAllNotes() {
        repository.deleteAllClientes()
    }

}