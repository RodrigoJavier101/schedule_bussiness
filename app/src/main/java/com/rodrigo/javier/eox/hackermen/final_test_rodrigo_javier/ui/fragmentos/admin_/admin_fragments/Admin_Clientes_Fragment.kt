package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.admin_.admin_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class Admin_Clientes_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_admin_clientes, container, false)

        return view
    }

    companion object {
        fun newInstance(): Admin_Clientes_Fragment {
            var newAdmin_Clientes_Fragment = Admin_Clientes_Fragment()
            return newAdmin_Clientes_Fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

    }

}