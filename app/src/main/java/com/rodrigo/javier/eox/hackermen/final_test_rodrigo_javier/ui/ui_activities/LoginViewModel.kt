package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GestionRepository
    val allUsers: LiveData<List<User_Entity>?>?

    init {
        repository = GestionRepository(application)
        allUsers = repository.allUsers_
    }

    fun insert(user: User_Entity?) {
        repository.insertUser(user)
    }

    fun update(user: User_Entity?) {
        repository.updateUser(user)
    }

    fun delete(user: User_Entity?) {
        repository.deleteUser(user)
    }

    fun deleteAllNotes() {
        repository.deleteAllClientes()
    }

}