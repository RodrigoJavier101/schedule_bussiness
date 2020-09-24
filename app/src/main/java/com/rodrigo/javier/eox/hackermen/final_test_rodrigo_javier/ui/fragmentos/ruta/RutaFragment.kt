package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ruta


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.ClientCertRequest
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.ItemClienteClickListener
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.RutaAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models.RutaViewModel
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_models.RutaViewModelViewModel
import kotlinx.android.synthetic.main.fragment_ruta.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class RutaFragment : Fragment(), ItemClienteClickListener {

    private lateinit var nombreCliente: TextView
    private lateinit var domicilioCliente: TextView
    private lateinit var telefonoCliente: TextView
    private lateinit var model: RutaViewModelViewModel
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RutaAdapter
    private lateinit var dao: GestionDao
    private lateinit var btnRuta: Button
    private lateinit var btnLimpiar: Button
    private lateinit var listaCliente: List<Clientes_Entity>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta, container, false)
        model = ViewModelProvider(requireActivity()).get(RutaViewModelViewModel::class.java)
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
        recyclerview.hasFixedSize()
        val divider =
            DividerItemDecoration(recyclerview.context, 1)
        recyclerview.addItemDecoration(divider)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {

            var lista: LiveData<List<Clientes_Entity>> =
                createClientesListFromDatabase()

            adapter = RutaAdapter(
                lista,
                this@RutaFragment, requireContext()
            )
            recyclerview.adapter = adapter


        }
    }


    suspend fun createClientesListFromDatabase(): LiveData<List<Clientes_Entity>> {
//        return dao.getAllFromClientesTable()
        return dao.getClienteInfo()
    }

    private fun initVariables() {
        listaCliente = listOf()
        dao = RoomApplication.gestionDatabase.getGestionDao()
        model = ViewModelProvider(requireActivity()).get(RutaViewModelViewModel::class.java)
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
                dao.insertClientes(cliente)
            }
            model.getClientes(cliente)
            Toast.makeText(
                context,
                nombreCliente.text.toString() + " - " + domicilioCliente.text.toString() + " - " + telefonoCliente.text.toString(),
                Toast.LENGTH_SHORT
            )
                .show()
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

    override fun itemClienteUpdateClick(cliente: Clientes_Entity) {
        Toast.makeText(context, cliente.nombre_cliente, Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteClientes(cliente)
        }
    }

}


/*
class RutaFragment : Fragment(), ItemClienteClickListener {

    lateinit var adapter: RutaAdapter
    lateinit var viewModel: RutaViewModel
    var list: MutableList<Clientes_Entity>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_ruta.layoutManager = LinearLayoutManager(context)
        adapter = RutaAdapter(list, this@RutaFragment, requireContext())
        recycler_ruta.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(RutaViewModel::class.java)
        viewModel.fetchAllData()
            .observe(viewLifecycleOwner, object : Observer<MutableList<Clientes_Entity>> {
                override fun onChanged(t: MutableList<Clientes_Entity>?) {
                    Log.v("OnChanged", "OnChanged!!")
                    adapter.addItems(t)
                }
            })

        */
/*add.setOnClickListener {
            openDialog()
        }*//*


        val model = ViewModelProviders.of(this).get(RutaViewModelViewModel::class.java)
        model.getClientes().observe(viewLifecycleOwner, { users ->
            // update UI
            Toast.makeText(context, "view model view model", Toast.LENGTH_SHORT).show()
        })

    }

    private fun openDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_agrega_user)
        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams().apply {
            copyFrom(dialog.window?.attributes)
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }

//        val submit = dialog.findViewById<View>(R.id.submit) as TextView
//        val name = dialog.findViewById<View>(R.id.ruta) as EditText
//        val author = dialog.findViewById<View>(R.id.author) as EditText
//        val genre = dialog.findViewById<View>(R.id.genre) as EditText

        */
/*  btn.setOnClickListener {
              when {
                  name.length() == 0 || author.length() == 0 || genre.length() == 0 ->
                      Toast.makeText(
                          this@DbActivity, "Please fill all the fields", Toast.LENGTH_SHORT
                      ).show()

                  else -> {
                      val cliente = Clientes_Entity(
                          name.text.toString(),
                          author.text.toString(),
                          genre.text.toString()
                      )
                      GestionDatabase.insertData(GestionDatabase.getInstance(this), cliente)
                      dialog.dismiss()
                  }
              }
          }*//*

        dialog.show()
        dialog.getWindow()?.setAttributes(lp)
    }

    override fun itemClienteUpdateClick(cliente: Clientes_Entity?) {
        Toast.makeText(context, "Click desde nuevo fragment", Toast.LENGTH_SHORT).show()
    }
}
*/
