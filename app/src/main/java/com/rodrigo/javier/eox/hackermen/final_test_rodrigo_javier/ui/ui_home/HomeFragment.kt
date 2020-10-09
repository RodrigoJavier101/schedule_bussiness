package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.home

import android.app.Service
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.ItemUserClickListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.ApiRetrofit
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.RetrofitClient
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.retrofit.api_objects.Json4Kotlin_Base
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_home.HomeAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_home.HomeViewModel
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.utilities.external.CommonFunctions
import kotlinx.android.synthetic.main.add_agrega_user.view.*
import kotlinx.android.synthetic.main.add_update_delete_user.view.*
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

    private lateinit var sharedPreferences: SharedPreferences

    /*vistas*/
    private lateinit var recyclerview: RecyclerView
    private lateinit var btnAdmin: Button
    private var nombreUser: TextView? = null
    private var domicilioPassUser: TextView? = null

    //    private var editTextNombreUser: EditText? = null
//    private var editTextPassUser: EditText? = null
    private lateinit var btnAgregarUser: Button

    private lateinit var adapter: HomeAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initializations(root)
        callIndicators()
        readFromSHPref()
        setUpBtnGestionar(root)

        /**********************/
        setUpBtnAgregar(root)


        var users_ddbb: List<User_Entity> = mutableListOf()

//            users_ddbb = createUsersListFromDatabase()
        recyclerview.hasFixedSize()
        val divider = DividerItemDecoration(recyclerview.context, 1)
        recyclerview.addItemDecoration(divider)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = HomeAdapter()
        recyclerview.adapter = adapter

        homeViewModel!!.allUsers!!.observe(viewLifecycleOwner, { clientes ->
            Log.d("-----------LOG------------->", clientes.toString())
            adapter!!.setUsers(clientes)
        })

        return root
    }

    private fun initializations(root: View) {
        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java);

        sharedPreferences = requireActivity().getSharedPreferences(
            CommonFunctions.fileNameShPref,
            Context.MODE_PRIVATE
        )

        btnAdmin = root.findViewById(R.id.btn_admin_)
        btnAgregarUser = root.findViewById<Button>(R.id.btn_agregar_user)
        recyclerview = root.findViewById(R.id.recycler_users)
        nombreUser = root.findViewById(R.id.lbl_item_nombre_user);
        domicilioPassUser = root.findViewById(R.id.lbl_item_password_user)

//        editTextNombreUser = root.findViewById(R.id.nombre_user_input)
//        editTextPassUser = root.findViewById(R.id.password_input)
    }

    private fun generateDialog() {
        val dialogView = layoutInflater
            .inflate(R.layout.add_agrega_user, null)

        val nombreuser_Input = dialogView.nombre_user_input
        val passwordInput = dialogView.password_input

        val dialogBuilder = AlertDialog
            .Builder(requireContext())
            .setTitle("Agrega un Usuario")
            .setView(dialogView)
            .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->

                if ((nombreuser_Input.text?.isNotEmpty()!! && passwordInput.text?.isNotEmpty()!!)
                    && (nombreuser_Input != null && passwordInput != null)
                ) {
                    var user: User_Entity = User_Entity(
                        user_name = nombreuser_Input.text.toString(),
                        password = Integer.parseInt(passwordInput.text.toString())
                    )
                    homeViewModel!!.insertUser(user)
                    Toast.makeText(context, "User --> DDBB", Toast.LENGTH_SHORT).show()
                    dialogView.nombre_user_input.setText("")
                    dialogView.password_input.setText("")

                    AsyncTask.execute {
                        var thread = Thread() {
                            fun run() {
                                try {
                                    synchronized(this) {
                                        Thread.sleep(500)
                                        UiThreadStatement.runOnUiThread {
                                            //                                                adapter.notifyDataSetChanged()
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


    override fun itemUserUpdateClick(user: User_Entity) {
        optionsAdmin(user)
    }


    private fun optionsAdmin(user: User_Entity) {
        if (user.user_name.equals("Admin")) {
            Toast.makeText(context, "No se puede modificar el Admin", Toast.LENGTH_SHORT).show()
        } else {
            val dialogView = layoutInflater
                .inflate(R.layout.add_update_delete_user, null)
            val nombreuser_update_Input = dialogView.nombre_user_update_input
            nombreuser_update_Input.setHint(user.user_name)
            val password_updateInput = dialogView.password_update_input
            password_updateInput.setHint(user.password.toString())

            val dialogBuilder = AlertDialog
                .Builder(requireContext())
                .setTitle("Agrega Update or Delete User")
                .setView(dialogView)
                .setNegativeButton("Delete") { dialog: DialogInterface, _: Int ->

                    CoroutineScope(Dispatchers.IO).launch {
//                        dao.deleteUser(user)
                    }

                    dialog.dismiss()
                }
                .setPositiveButton("Update") { dialog: DialogInterface, _: Int ->

                    if (dialogView.nombre_user_update_input.text.toString()
                            .isNotBlank() || dialogView.nombre_user_update_input.text.toString()
                            .isNotEmpty()
                    ) {
                        user.user_name = dialogView.nombre_user_update_input.text.toString()
                    }
                    if (dialogView.password_update_input.text.toString()
                            .isNotBlank() || dialogView.password_update_input.text.toString()
                            .isNotEmpty()
                    ) {
                        user.password = dialogView.password_update_input.text.toString().toInt()
                    }
                    CoroutineScope(Dispatchers.IO).launch {
//                        dao.updateUser(user)
                    }
//                    model.setSelected(user)
                    if (nombreuser_update_Input.text?.isNotEmpty()!! && password_updateInput.text?.isNotEmpty()!!) {

                        if (nombreuser_update_Input != null && password_updateInput != null) {

                            AsyncTask.execute {
                                var thread = Thread() {
                                    fun run() {
                                        try {
                                            synchronized(this) {
                                                Thread.sleep(500)
                                                UiThreadStatement.runOnUiThread {
//                                                    adapter.notifyDataSetChanged()
                                                    dialog.dismiss()
                                                }
                                            }
                                        } catch (e: InterruptedException) {
                                            Toast.makeText(context, e.message, Toast.LENGTH_LONG)
                                                .show()
                                        }
                                    }
                                }
                                thread.start()
                            }

                        }

                    }
                }
            dialogBuilder.create().show()
        }
    }

    private fun setUpBtnAgregar(view: View?) {
        btnAgregarUser.setOnClickListener {
            generateDialog()
        }
    }

    private fun readFromSHPref() {
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

    private fun setUpBtnGestionar(view: View) {
         btnAdmin.setOnClickListener {
             setVisibilityView(view)
         }

        /*checkearlo, parece que solo debe ir en una activity*/
      /*  var layoutInflater: LayoutInflater
        layoutInflater = LayoutInflater.from(this.requireContext())
        layoutInflater =
            getSystemServiceName(this.requireContext(), Service::class.java) as LayoutInflater
        val viewPopupwindow: View = layoutInflater!!.inflate(R.layout.popupwindowlayout, null)
        val popupWindow = PopupWindow(viewPopupwindow, 900, 500, true)
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0)
        viewPopupwindow.setOnTouchListener { v, event ->
            popupWindow.dismiss()
            true

        }*/

    }

     private fun setVisibilityView(view: View) {
         btnAgregarUser.visibility = View.VISIBLE
         recyclerview.visibility = View.VISIBLE
     }

}

