package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class RutaViewModel(application: Application) : AndroidViewModel(application) {

    var list: LiveData<MutableList<Clientes_Entity>>

    init {
        list = GestionDatabase.getData(GestionDatabase.getInstance(this.getApplication()))
    }

    fun fetchAllData(): LiveData<MutableList<Clientes_Entity>>? = list
}
