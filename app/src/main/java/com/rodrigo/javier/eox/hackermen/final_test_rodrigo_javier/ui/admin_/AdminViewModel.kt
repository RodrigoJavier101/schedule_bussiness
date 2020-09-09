package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdminViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ADMIN Fragment"
    }
    val text: LiveData<String> = _text
}