package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionRepository
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity

class ViewModelEspecial(application: Application) : AndroidViewModel(application) {
    private val repository: GestionRepository
    val todos_los_productos: List<Productos_Entity>

    init {
        val dao = GestionDatabase.getDatabase(application).getGestionDao()
        repository = GestionRepository()
        todos_los_productos = repository.allProductos
    }

    fun listarProductos(){
        repository.allProductos
    }
}