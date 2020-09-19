package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ruta


import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.api_objects.Json4Kotlin_Base
import kotlinx.android.synthetic.main.add_ruta_dialog.view.*
import retrofit2.Call

class RutaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta, container, false)


        setUpAddButton(view)
        return view
    }


    companion object {
        fun newInstance(): RutaFragment {
            var newRutaFragment = RutaFragment()
            return newRutaFragment
        }
    }

    private fun setUpAddButton(view: View) {
        var btn_ruta = view.findViewById<Button>(R.id.btn_crear_ruta)

        btn_ruta.setOnClickListener {
            generateDialog()
        }
    }

    private fun generateDialog() {
        val dialogView = layoutInflater
            .inflate(R.layout.add_ruta_dialog, null)
        val calle_Input = dialogView.nombre_calle_input
        val nombreCliente_Input = dialogView.nombre_cliente_input
        val telefono_Input = dialogView.telefono_cliente_input
        val dialogBuilder = AlertDialog
            .Builder(requireContext())
            .setTitle("Item de Ruta")
            .setView(dialogView)
            .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->

                if (calle_Input.text?.isNotEmpty()!! && nombreCliente_Input.text?.isNotEmpty()!! && telefono_Input.text?.isNotEmpty()!!) {

                    AsyncTask.execute {
                        if (calle_Input != null) {

                        }
                        var thread = Thread() {
                            fun run() {
                                try {
                                    synchronized(this) {
                                        Thread.sleep(500)
                                        UiThreadStatement.runOnUiThread {

                                            dialog.dismiss()
                                        }
                                    }
                                } catch (e: InterruptedException) {
                                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        thread.start()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Ningún campo debe estar vacío",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        dialogBuilder.create().show()
    }

}