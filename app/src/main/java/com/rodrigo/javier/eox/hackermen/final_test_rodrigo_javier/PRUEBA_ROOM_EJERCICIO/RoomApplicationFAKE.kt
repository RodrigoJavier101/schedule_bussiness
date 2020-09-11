package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.PRUEBA_ROOM_EJERCICIO

import android.app.Application
import androidx.room.Room

class RoomApplicationFAKE: Application() {
    companion object {
        var recommendationsDatabase:RecommendationsDatabase? = null
    }
    override fun onCreate() {
        super.onCreate()
        recommendationsDatabase = Room
            .databaseBuilder(this,
                RecommendationsDatabase::class.java,
                "recommendations-master-db").build()
    }
}