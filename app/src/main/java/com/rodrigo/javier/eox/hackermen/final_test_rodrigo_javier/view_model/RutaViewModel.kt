package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RutaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Set your mind here...!"
    }
    val text: LiveData<String> = _text
}