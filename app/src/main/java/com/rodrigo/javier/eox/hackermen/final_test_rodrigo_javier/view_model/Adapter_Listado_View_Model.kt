package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_model

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Adapter_Listado_View_Model : ViewModel() {

    val touchedItem: MutableLiveData<TextView> by lazy {
        MutableLiveData<TextView>()
    }
}