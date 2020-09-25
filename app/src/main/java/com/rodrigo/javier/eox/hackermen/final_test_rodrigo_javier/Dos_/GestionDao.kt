package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GestionDao {
    @Insert
    fun insert(note: Clientes_Entity?)

    @Update
    fun update(note: Clientes_Entity?)

    /*EJEMPLO DE CÓDIGO
    *  @Update(onConflict = REPLACE)
    fun updateCliente(cliente: Clientes_Entity)
*/

    @Delete
    fun delete(note: Clientes_Entity?)

    @Query("delete from clientes_table")
    fun deleteAllNotes()

    @Query("select * from clientes_table order by priority desc")
    fun getAllNotes(): LiveData<List<Clientes_Entity>?>?
}