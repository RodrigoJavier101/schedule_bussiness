package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.agenda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgendaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is AGENDA Fragment"
    }
    val text: LiveData<String> = _text
}