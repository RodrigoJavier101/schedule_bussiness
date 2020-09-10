package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.admin_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import kotlinx.android.synthetic.main.fragment_admin_clientes.*
import kotlinx.android.synthetic.main.fragment_admin_proveedores.*


class Admin_Proveedores_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_admin_proveedores, container, false)

        return view
    }

    companion object {
        fun newInstance(): Admin_Proveedores_Fragment {
            var newAdmin_Proveedores_Fragment = Admin_Proveedores_Fragment()
            return newAdmin_Proveedores_Fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        lbl_admin_proveedores.text = "desde 'onViewCreated admin proveedores"
    }
}
