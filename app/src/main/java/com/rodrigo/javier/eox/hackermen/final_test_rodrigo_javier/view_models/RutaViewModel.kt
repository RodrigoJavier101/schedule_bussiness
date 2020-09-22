package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Ruta_Entity

class RutaViewModel:ViewModel() {

    private val elemento = MutableLiveData<Clientes_Entity>()

    fun setElemento(item: Clientes_Entity) {
        elemento.value = item
    }

    fun getElemento(): MutableLiveData<Clientes_Entity> {
        return elemento
    }
}