package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.view_model.VentasViewModel

class VentasFragment : Fragment() {

    private lateinit var ingresosViewModel: VentasViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ingresosViewModel =
            ViewModelProviders.of(this).get(VentasViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_ventas, container, false)
//        val textView: TextView = view.findViewById(R.id.text_ingresos)
        ingresosViewModel.text_.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        return view
    }

    companion object {
        fun newInstance(): VentasFragment {
            var newIngresosFragment = VentasFragment()
            return newIngresosFragment
        }
    }
}