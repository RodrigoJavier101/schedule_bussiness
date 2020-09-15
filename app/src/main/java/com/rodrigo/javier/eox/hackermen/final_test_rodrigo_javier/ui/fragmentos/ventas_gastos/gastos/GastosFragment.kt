package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.gastos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class GastosFragment : Fragment() {

    private lateinit var GastosViewModel: GastosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        GastosViewModel = ViewModelProviders.of(this).get(GastosViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_gastos, container, false)
//        val textView: TextView = view.findViewById(R.id.text_gastos)
//        GastosViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return view
    }

    companion object {
        fun newInstance(): GastosFragment {
            var newGastosFragment = GastosFragment()
            return newGastosFragment
        }
    }
}