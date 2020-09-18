package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models

import android.content.ClipData.Item
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity


class ListaViewModel:ViewModel() {

    private val selected = MutableLiveData<Productos_Entity>()

    fun setSelected(item: Productos_Entity) {
        selected.value = item
    }

    fun getSelected(): MutableLiveData<Productos_Entity>? {
        return selected
    }
}