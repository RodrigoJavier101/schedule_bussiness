package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_.ItemClienteClickListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R


private lateinit var btnRuta: Button
private lateinit var btnLimpiar: Button


private fun setUpLimpiarButton(view: View?) {
    btnLimpiar.setOnClickListener {
        limpiarInputs()
    }
}


private fun initViews(view: View) {
    btnRuta = view.findViewById<Button>(R.id.btn_crear_ruta)
    btnLimpiar = view.findViewById<Button>(R.id.btn_limpiar_ruta)


}


private fun setUpAddButton(view: View) {
    btnRuta.setOnClickListener {
        ingresaRuta()
    }
}

private fun ingresaRuta() {
    if (nombreCliente.text.isNotEmpty() && domicilioCliente.text.isNotEmpty() && telefonoCliente.text.isNotEmpty()) {
        var cliente =
            Clientes_Entity(
                nombre_cliente = nombreCliente.text.toString(),
                domicilio_cliente = domicilioCliente.text.toString(),
                telefono_cliente = telefonoCliente.text.toString().toLong()
            )


        limpiarInputs()
    } else {
        Toast.makeText(
            context,
            "Ningún campo debe estar vacío",
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun limpiarInputs() {
    nombreCliente.setText("")
    domicilioCliente.setText("")
    telefonoCliente.setText("")
}

override fun itemClienteUpdateClick(cliente: Clientes_Entity?) {
    Toast.makeText(context, cliente?.nombre_cliente, Toast.LENGTH_SHORT).show()

}

