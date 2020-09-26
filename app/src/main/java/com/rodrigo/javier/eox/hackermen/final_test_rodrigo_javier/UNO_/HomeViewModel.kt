package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity

class HomeViewModel : ViewModel() {
    private val user_ = MutableLiveData<User_Entity>()
    private val deleted_user = MutableLiveData<User_Entity>()
    fun setSelected(item: User_Entity) {
        user_.value = item
    }

    fun getSelected(): MutableLiveData<User_Entity>? {
        return user_
    };

    fun setDeleted(item: User_Entity) {
        user_.value = item
    }

    fun getDeleted(): MutableLiveData<User_Entity> {
        return deleted_user
    }
}