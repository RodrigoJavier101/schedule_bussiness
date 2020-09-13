package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ingresos.fragments_interiores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class Listado_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.listado_inventario_fragment, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        return view
    }

  companion object{  fun newInstance(): Listado_Fragment {
        var newListado_Fragment = Listado_Fragment()

        return newListado_Fragment
    }}
}