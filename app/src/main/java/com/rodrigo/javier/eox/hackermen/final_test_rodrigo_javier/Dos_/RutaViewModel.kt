package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


/*subclass de view model, éste permite pasarle applciation enel constructor por lo  que no se
necesita context ???, si se guarda el context en la act habran memory leaks
se deberia pasar el */
class RutaViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GestionRepository
    val allNotes: LiveData<List<Clientes_Entity>?>?

    init {
        repository = GestionRepository(application)
        allNotes = repository.allNotes
    }
    /*la activity solamente tiene una referencia al viewmodel por lo que se necesita nuevamente
    mets para traer datos desde repository*/
    fun insert(note: Clientes_Entity?) {
        repository.insert(note)
    }

    fun update(note: Clientes_Entity?) {
        repository.update(note)
    }

    fun delete(note: Clientes_Entity?) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

}