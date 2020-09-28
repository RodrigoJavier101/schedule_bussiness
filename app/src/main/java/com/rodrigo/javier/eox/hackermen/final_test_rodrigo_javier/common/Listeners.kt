package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces

import android.view.View
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.Productos_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity

interface CardViewListenerShortClick {
    /*para listar una venta*/
    fun cardViewClickedShort(producto: Productos_Entity, view: View, position: Int)
}

interface CardViewListenerLongClick {
    /*para eliminar item producto*/
    fun cardViewClickedLong(producto: Productos_Entity)
}

interface ItemUserClickListener {
    /*para actualizar user*/
    fun itemUserUpdateClick(user: User_Entity)
}

interface ItemClienteClickListener {
    /*para actualizar user*/
    fun itemClienteUpdateClick(cliente: Clientes_Entity?)
}