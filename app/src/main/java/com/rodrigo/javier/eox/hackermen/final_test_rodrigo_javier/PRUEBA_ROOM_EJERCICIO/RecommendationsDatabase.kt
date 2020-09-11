package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.PRUEBA_ROOM_EJERCICIO

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(RecommendationEntity::class)], version = 1, exportSchema = false)
//@TypeConverters(RecommendationsConverter::class)
abstract class RecommendationsDatabase : RoomDatabase() {
    abstract fun getRecommendationsDAO(): RecommendationsDAO
}