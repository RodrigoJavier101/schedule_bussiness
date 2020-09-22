package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ruta


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.ItemClienteClickListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.Clientes_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.GestionDao
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.RoomApplication
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.RutaAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models.RutaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class RutaFragment : Fragment(), ItemClienteClickListener {

    private lateinit var nombreCliente: TextView
    private lateinit var domicilioCliente: TextView
    private lateinit var telefonoCliente: TextView
    private lateinit var listaUsers: MutableLiveData<ArrayList<User_Entity>>
    private lateinit var model: RutaViewModel
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RutaAdapter
    private lateinit var dao: GestionDao
    private lateinit var btnRuta: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta, container, false)
        model = ViewModelProvider(requireActivity()).get(RutaViewModel::class.java)
        initViews(view)
        initVariables()
        initRecycler()

        setUpAddButton(view)
        return view
    }

    private fun initRecycler() {
        recyclerview.hasFixedSize()
        val divider =
            DividerItemDecoration(recyclerview.context, 1)
        recyclerview.addItemDecoration(divider)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            var clientesddbb = createClientesListFromDatabase()
            adapter = RutaAdapter(
                clientesddbb,
                this@RutaFragment

            )
            recyclerview.adapter = adapter
        }
        model.getElemento()?.observe(viewLifecycleOwner) {
            var lista: ArrayList<Clientes_Entity> = arrayListOf()
            lista.add(it)
            adapter = RutaAdapter(
                lista,
                this@RutaFragment

            )
            recyclerview.adapter = adapter
        }


    }

    private fun createClientesListFromDatabase(): List<Clientes_Entity> {
        return dao.getAllFromClientesTable()
    }

    private fun initVariables() {
        dao = RoomApplication.gestionDatabase.getGestionDao()
        model = ViewModelProvider(requireActivity()).get(RutaViewModel::class.java)
    }

    private fun initViews(view: View) {
        btnRuta = view.findViewById<Button>(R.id.btn_crear_ruta)
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
                dao.insertClientes(cliente)
            }
            model.setElemento(cliente)
            Toast.makeText(
                context,
                nombreCliente.text.toString() + " - " + domicilioCliente.text.toString() + " - " + telefonoCliente.text.toString(),
                Toast.LENGTH_SHORT
            )
                .show()

        } else {
            Toast.makeText(
                context,
                "Ningún campo debe estar vacío",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun itemClienteUpdateClick(cliente: Clientes_Entity) {
//        model.setElemento(cliente)
        Toast.makeText(context, cliente.nombre_cliente, Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteClientes(cliente)
        }
    }
}