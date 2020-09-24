package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ruta.RutaFragment
import kotlinx.coroutines.withContext
import org.w3c.dom.Entity

class RutaViewModel(application: Application) : AndroidViewModel(application) {

    var list: LiveData<MutableList<Clientes_Entity>>

    init {
//        list = GestionDatabase.getInstance(this, GestionDatabase.getInstance(this.getApplication()))
        list = GestionDatabase.getData(GestionDatabase.getInstance(this.getApplication()))
    }


    fun fetchAllData(): LiveData<MutableList<Clientes_Entity>> = list
}
