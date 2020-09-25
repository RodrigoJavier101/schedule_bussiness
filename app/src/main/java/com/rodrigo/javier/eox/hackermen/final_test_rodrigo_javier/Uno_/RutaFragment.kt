package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RutaFragment : Fragment(), ItemClienteClickListener {

    private lateinit var nombreCliente: TextView
    private lateinit var domicilioCliente: TextView
    private lateinit var telefonoCliente: TextView
    private lateinit var model: RutaViewModel
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RutaAdapter
    private lateinit var dao: GestionDao
    private lateinit var btnRuta: Button
    private lateinit var btnLimpiar: Button
    var list: LiveData<MutableList<Clientes_Entity>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta, container, false)
        initViews(view)
        initVariables()
        initRecycler()
        setUpAddButton(view)
        setUpLimpiarButton(view)
        return view
    }

    private fun setUpLimpiarButton(view: View?) {
        btnLimpiar.setOnClickListener {
            limpiarInputs()
        }
    }

    private fun initRecycler() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        list = createClientesListFromDatabase()
        adapter = RutaAdapter(list, this, requireContext())
        recyclerview.adapter = adapter
        adapter.addItems(list)
    }

    fun createClientesListFromDatabase(): LiveData<MutableList<Clientes_Entity>> {
        return dao.getAllFromClientesTable()
    }

    private fun initVariables() {
        dao = RoomApplication.gestionDatabase.getGestionDao()
        model = ViewModelProvider(requireActivity()).get(RutaViewModel::class.java)
    }

    private fun initViews(view: View) {
        btnRuta = view.findViewById<Button>(R.id.btn_crear_ruta)
        btnLimpiar = view.findViewById<Button>(R.id.btn_limpiar_ruta)
        nombreCliente = view.findViewById(R.id.cliente_ruta_input)
        domicilioCliente = view.findViewById(R.id.domicilio_cliente_ruta_input)
        telefonoCliente = view.findViewById(R.id.telefono_cliente_ruta_input)
        recyclerview = view.findViewById(R.id.recycler_ruta)
    }

    companion object {
        fun newInstance(): RutaFragment {
            var newRutaFragment = RutaFragment()
            return newRutaFragment
        }
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

            CoroutineScope(Dispatchers.IO).launch {
                dao.insertCliente(cliente)
            }
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
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteCliente(cliente)
        }
    }

}


/*
        viewModel = ViewModelProviders.of(this).get(RutaViewModel::class.java)
        viewModel.fetchAllData()
            .observe(viewLifecycleOwner, object : Observer<MutableList<Clientes_Entity>> {
                override fun onChanged(t: MutableList<Clientes_Entity>?) {
                    Log.v("OnChanged", "OnChanged!!")
                    adapter.addItems(t)
                }
            })


        val model = ViewModelProviders.of(this).get(RutaViewModelViewModel::class.java)
        model.getClientes().observe(viewLifecycleOwner, { users ->
            // update UI
            Toast.makeText(context, "view model view model", Toast.LENGTH_SHORT).show()
        })

    }
*/
