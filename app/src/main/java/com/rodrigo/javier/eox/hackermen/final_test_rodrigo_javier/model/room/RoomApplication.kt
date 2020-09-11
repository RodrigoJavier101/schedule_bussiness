package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import android.app.Application
import androidx.room.Room

class RoomApplication : Application() {
    companion object {
        var gestionDatabase: GestionDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        gestionDatabase =
            Room.databaseBuilder(this, GestionDatabase::class.java, "gestion-negocios-db").build()
    }
}