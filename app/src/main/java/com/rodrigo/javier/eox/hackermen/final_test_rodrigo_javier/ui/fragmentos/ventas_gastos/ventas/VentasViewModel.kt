package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VentasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is INGRESOS Fragment"
    }
    val text_: LiveData<String> = _text
}