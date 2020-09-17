package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.home

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.RetrofitClient
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.api_objects.Json4Kotlin_Base
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Productos_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import kotlinx.android.synthetic.main.add_producto_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var service: ApiRetrofit
    private var call: Call<Json4Kotlin_Base>? = null
    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        callIndicators()

        setUpAddButton(root)
        return root
    }

    fun callIndicators() {

        service =
            RetrofitClient.getRetrofitObject()
        call = service.getPhrase()

        call!!.enqueue(object : Callback<Json4Kotlin_Base> {

            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: Response<Json4Kotlin_Base>
            ) {
                val json: Json4Kotlin_Base

                try {
                    json = response.body()!!
                    lbl_dolar.text = json.dolar.valor.toString()
                    lbl_euro.text = json.euro.valor.toString()
                    lbl_uf.text = json.uf.valor.toString()
                    lbl_utm.text = json.utm.valor.toString()
                    lbl_desempleo.text = json.tasa_desempleo.valor.toString() + "%"
                } catch (t: Throwable) {
                    Log.d("ERROR capa 8 --->", t.message.toString())
                }
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {

            }
        })
    }

    companion object {
        fun newInstance(): HomeFragment {
            var newHomeFragment = HomeFragment()
            return newHomeFragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        call!!.cancel()
    }

    private fun setUpAddButton(view: View) {
        var btn_agregar = view.findViewById<Button>(R.id.btn_agregar_producto)

        btn_agregar.setOnClickListener {
            generateDialog()
        }
    }

    private fun generateDialog() {
        val dialogView = layoutInflater
            .inflate(R.layout.add_producto_dialog, null)
        val producto_nombre_Input = dialogView.nombre_producto_input
        val producto_precio_Input = dialogView.precio_producto_input
        val dialogBuilder = AlertDialog
            .Builder(requireContext())
            .setTitle("Agrega un Producto")
            .setView(dialogView)
            .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->

                if (producto_nombre_Input.text?.isNotEmpty()!! && producto_precio_Input.text?.isNotEmpty()!!) {

                    AsyncTask.execute {
                        if (producto_nombre_Input != null && producto_precio_Input != null) {
                            dao.insertProductos(
                                insertProducto(
                                    producto_nombre_Input.text.toString(),
                                    producto_precio_Input.text.toString()
                                )
                            )
                        }

                        var thread = Thread() {
                            fun run() {
                                try {
                                    synchronized(this) {
                                        Thread.sleep(500)
                                        UiThreadStatement.runOnUiThread {
                                            //                                                adapter.updateData(newItems)
                                            Toast.makeText(
                                                context,
                                                "Item en la tabla 'PRODUCTOS'",
                                                Toast.LENGTH_SHORT
                                            ).show()
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

    fun insertProducto(nombre_prod: String, precio_prod: String): Productos_Entity {
        var producto = Productos_Entity(
            nombre_producto = nombre_prod, precio_producto = precio_prod.toInt()
        )
        return producto
    }

}
