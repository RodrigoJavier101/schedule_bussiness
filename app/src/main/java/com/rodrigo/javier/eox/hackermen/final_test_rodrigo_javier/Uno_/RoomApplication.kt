package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import android.app.Application
import androidx.room.Room

class RoomApplication : Application() {
    companion object {
        lateinit var gestionDatabase: GestionDatabase
    }

    override fun onCreate() {
        super.onCreate()
        gestionDatabase =
            Room.databaseBuilder(this, GestionDatabase::class.java, "gestion-negocios-db").build()

/*SOLO PARA EVITAR MIGRACIONES BORRA TODA LA BASE DE DATOS*/
//            Room.databaseBuilder(this, GestionDatabase::class.java, "gestion-negocios-db")
//                .fallbackToDestructiveMigration().build()
    }
}