package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.PRUEBA_ROOM_EJERCICIO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecommendationsDAO {
    @Query("SELECT * FROM recommendations_list")
    fun getAllRecommendations(): List<RecommendationEntity>

    @Insert
    fun insertRecommendations(recommendations: RecommendationEntity)
}