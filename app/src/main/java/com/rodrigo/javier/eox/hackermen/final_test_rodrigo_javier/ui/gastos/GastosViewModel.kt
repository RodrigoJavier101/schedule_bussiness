package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.gastos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GastosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is GASTOS Fragment"
    }
    val text: LiveData<String> = _text
}