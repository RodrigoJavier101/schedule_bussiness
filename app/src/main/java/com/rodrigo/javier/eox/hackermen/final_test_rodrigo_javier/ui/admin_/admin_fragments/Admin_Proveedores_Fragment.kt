package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.admin_fragments

import android.os.Bundle
import android.renderscript.AllocationAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters.AdminProveedoresRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_admin_proveedores.*


class Admin_Proveedores_Fragment : Fragment() {

    //    private lateinit var list_recyclerview: RecyclerView
    private lateinit var adapterAdminProveedoresRecyclerAdapter: AdminProveedoresRecyclerAdapter

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

    }

    override fun onResume() {
        super.onResume()
        setViewsOnScreen()
    }

    private fun setViewsOnScreen() {
        recycler_item_proveedores.layoutManager = LinearLayoutManager(requireContext())
        adapterAdminProveedoresRecyclerAdapter =
            AdminProveedoresRecyclerAdapter(mutableListOf(), requireContext())
        recycler_item_proveedores.adapter = adapterAdminProveedoresRecyclerAdapter
    }
}
