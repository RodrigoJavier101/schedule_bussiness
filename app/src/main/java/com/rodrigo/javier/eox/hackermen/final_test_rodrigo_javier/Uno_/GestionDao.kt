package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface GestionDao {

    @Query("SELECT * FROM clientes_table")
    fun getAllFromClientesTable(): LiveData<MutableList<Clientes_Entity>>

    @Insert
    fun insertCliente(cliente: Clientes_Entity)

    @Update(onConflict = REPLACE)
    fun updateCliente(cliente: Clientes_Entity)

    @Delete
    fun deleteCliente(cliente: Clientes_Entity?)

    /***************************/

}