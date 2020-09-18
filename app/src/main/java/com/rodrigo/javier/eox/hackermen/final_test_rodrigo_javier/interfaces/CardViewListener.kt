package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces

import android.view.View
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity

interface CardViewListenerShortClick {
    fun cardViewClickedShort(producto: Productos_Entity,view: View, position: Int)
}

interface CardViewListenerLongClick{
    fun cardViewClickedLong(producto: Productos_Entity)
}