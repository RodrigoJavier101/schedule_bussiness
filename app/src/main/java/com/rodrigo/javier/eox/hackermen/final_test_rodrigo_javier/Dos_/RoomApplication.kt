package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.app.Application
import android.content.Context
import androidx.room.Room

class RoomApplication : Application() {
    companion object {
        private var instance: GestionDatabase? = null
//        lateinit var gestionDatabase: GestionDatabase
    }

    override fun onCreate() {
        super.onCreate()
//        gestionDatabase =
//            Room.databaseBuilder(this, GestionDatabase::class.java, "gestion-negocios-db").build()

/*SOLO PARA EVITAR MIGRACIONES BORRA TODA LA BASE DE DATOS*/
//            Room.databaseBuilder(this, GestionDatabase::class.java, "gestion-negocios-db")
//                .fallbackToDestructiveMigration().build()


//    /*****************************************************************************************************************************************************************************/
        /*se crea la data base con otro singleton*/
        @Synchronized
        fun getInstance(context: Context): GestionDatabase? {
            /*se usa una sola instancia de la ddbb*/
            /*syn sig que una sola vez se use el metodo si es que instance es null*/
            if (instance == null) {
                /*no se llama Note database porque es abstract*/
                /*en su lugar se usa builder*/
                instance = Room.databaseBuilder(
                    context.applicationContext, GestionDatabase::class.java,
                    "gestion-negocios-db"
                )
//                        .fallbackToDestructiveMigration() /*----permite crear algo en la ddb cuandro se crea por primera vez----*/
                    .addCallback(GestionDatabase.roomcallback)
                    .build()
            }
            return instance
        }

    }

}