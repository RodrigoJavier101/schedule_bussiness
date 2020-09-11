package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.PRUEBA_ROOM_EJERCICIO

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recommendations_list")
data class RecommendationEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var recommendation: String,
    var isCheck: Boolean
)