package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.admin_

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdminViewModel : ViewModel() {

    private val mutable = MutableLiveData<String>().apply {
        value = "This is ADMIN Fragment"
    }
    val liveData: LiveData<String> = mutable
}