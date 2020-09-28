package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.gastos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class GastosFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gastos, container, false)

        return view
    }

    companion object {
        fun newInstance(): GastosFragment {
            var newGastosFragment = GastosFragment()
            return newGastosFragment
        }
    }
}