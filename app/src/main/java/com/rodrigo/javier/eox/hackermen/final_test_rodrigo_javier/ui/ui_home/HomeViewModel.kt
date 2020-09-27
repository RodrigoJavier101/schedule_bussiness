package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.GestionRepository
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GestionRepository
    val allUsers: LiveData<List<User_Entity>?>?

    init {
        repository = GestionRepository(application)
        allUsers = repository.allUsers_
    }

    fun insertUser(user: User_Entity?) {
        repository.insertUser(user)
    }

    fun updateUser(user: User_Entity?) {
        repository.updateUser(user)
    }

    fun deleteUser(user: User_Entity?) {
        repository.deleteUser(user)
    }

    fun deleteAllUsers() {
        repository.deleteAllUsers()
    }

}