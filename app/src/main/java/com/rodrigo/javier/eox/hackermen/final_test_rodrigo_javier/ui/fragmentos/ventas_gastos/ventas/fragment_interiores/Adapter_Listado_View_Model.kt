package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.fragment_interiores

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Adapter_Listado_View_Model : ViewModel() {

    val touchedItem: MutableLiveData<TextView> by lazy {
        MutableLiveData<TextView>()
    }
}