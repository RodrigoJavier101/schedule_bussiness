package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ingresos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.data_view.Productos_DataView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.DataEjemplo.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters.ListadoInventarioAdapter

class IngresosFragment : Fragment() {

    private lateinit var ingresosViewModel: IngresosViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ingresosViewModel =
            ViewModelProviders.of(this).get(IngresosViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_ingresos, container, false)
//        val textView: TextView = view.findViewById(R.id.text_ingresos)
        ingresosViewModel.text_.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        return view
    }

    companion object {
        fun newInstance(): IngresosFragment {
            var newIngresosFragment = IngresosFragment()
            return newIngresosFragment
        }
    }
}