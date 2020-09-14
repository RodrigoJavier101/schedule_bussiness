package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.admin_.admin_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters.AdminProveedoresRecyclerAdapter


class Admin_Estadisticas_Fragment : Fragment() {

    //    private lateinit var list_recyclerview: RecyclerView
    private lateinit var adapterAdminProveedoresRecyclerAdapter: AdminProveedoresRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_admin_estadisticas, container, false)

        return view
    }

    companion object {
        fun newInstance(): Admin_Estadisticas_Fragment {
            var newAdmin_Estaditicas_Fragment = Admin_Estadisticas_Fragment()
            return newAdmin_Estaditicas_Fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        setViewsOnScreen()
    }

    private fun setViewsOnScreen() {

    }
}
