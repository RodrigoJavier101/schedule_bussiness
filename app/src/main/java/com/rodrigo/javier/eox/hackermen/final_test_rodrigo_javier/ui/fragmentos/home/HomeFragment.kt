package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.home

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.ItemUserClickListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.RetrofitClient
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.api_objects.Json4Kotlin_Base
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.SimpleAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions
import kotlinx.android.synthetic.main.add_producto_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(), ItemUserClickListener {

    private lateinit var service: ApiRetrofit
    private var call: Call<Json4Kotlin_Base>? = null
    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var btnAdmin: Button
    lateinit var btnAgregarUser: Button
    private lateinit var adapter: SimpleAdapter
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        sharedPreferences = requireActivity().getSharedPreferences(
            CommonFunctions.fileNameShPref,
            Context.MODE_PRIVATE
        )

        recyclerview = root.findViewById(R.id.recycler_users)
        recyclerview.hasFixedSize()
        val divider = DividerItemDecoration(recyclerview.context, 1)
        recyclerview.addItemDecoration(divider)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())


        CoroutineScope(Dispatchers.IO).launch {
            val users_ddbb = createUsersListFromDatabase()
            adapter = SimpleAdapter(users_ddbb, this@HomeFragment)
            recyclerview.adapter = adapter
        }


        btnAdmin = root.findViewById(R.id.btn_admin_)
        btnAgregarUser = root.findViewById<Button>(R.id.btn_agregar_user)
        callIndicators()
        readFromSHPref()
        setUpAddButtonGestionar(root)
        setUpAddButtonAgregar(root)
        return root
    }

    private fun setUpAddButtonAgregar(view: View?) {
        btnAgregarUser.setOnClickListener {
            generateDialog()
        }
    }

    private fun createUsersListFromDatabase(): List<User_Entity> {
        val users_db = dao.getAllFromUserTable()
        return users_db
    }

    private fun generateDialog() {
        val dialogView = layoutInflater
            .inflate(R.layout.add_agrega_user, null)
        val producto_nombre_Input = dialogView.nombre_producto_input
        val producto_precio_Input = dialogView.precio_producto_input
        val dialogBuilder = AlertDialog
            .Builder(requireContext())
            .setTitle("Agrega un Usuario")
            .setView(dialogView)
            .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->

                if (producto_nombre_Input.text?.isNotEmpty()!! && producto_precio_Input.text?.isNotEmpty()!!) {

                    AsyncTask.execute {
                        if (producto_nombre_Input != null && producto_precio_Input != null) {
                            /*dao.insertProductos(

                            )*/
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


    private fun readFromSHPref() {
        /* Toast.makeText(
             context,
             sharedPreferences.getString("NombreUsuario", "") + " - " +
                     sharedPreferences.getString("PasswordUsuario", ""),
             Toast.LENGTH_LONG
         ).show()*/
        if (sharedPreferences.getString("NombreUsuario", "").equals("Admin")) {
            btnAdmin.visibility = View.VISIBLE
        } else {
            btnAdmin.visibility = View.GONE
        }
    }

    private fun callIndicators() {

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

    private fun setUpAddButtonGestionar(view: View) {
        btnAdmin.setOnClickListener {
            setVisibilityView(view)
        }
    }

    private fun setVisibilityView(view: View) {

        var recyclerAgregarUser = view.findViewById<RecyclerView>(R.id.recycler_users)
        btnAgregarUser.visibility = View.VISIBLE
        recyclerAgregarUser.visibility = View.VISIBLE
    }

    override fun itemUserUpdateClick(user: User_Entity) {
        Toast.makeText(context, user.user_name, Toast.LENGTH_SHORT).show()
    }
}
