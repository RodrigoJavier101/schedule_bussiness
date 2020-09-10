package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters.AdminViewPagerAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.admin_fragments.Admin_Clientes_Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.admin_fragments.Admin_Proveedores_Fragment
import kotlinx.android.synthetic.main.fragment_admin.*

class AdminFragment : Fragment() {

    private lateinit var adminViewModel: AdminViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adminViewModel =
            ViewModelProviders.of(this).get(AdminViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_admin, container, false)
//        val textView: TextView = root.findViewById(R.id.text_admin)


        val view_pager_adapter = AdminViewPagerAdapter(requireActivity().supportFragmentManager)
        adminViewModel.liveData.observe(viewLifecycleOwner, Observer {
            lbl_frag_adm.text = it


            view_pager_adapter.addFragment(
                Admin_Clientes_Fragment.newInstance(),
                "Gestion Clientes"
            )
            view_pager_adapter.addFragment(
                Admin_Proveedores_Fragment.newInstance(),
                "Gestion Proveedores"
            )

            view_pager_admin.adapter = view_pager_adapter
            tab_layout_admin.setupWithViewPager(view_pager_admin)

        })


        return view
    }

    companion object {
        fun newInstance(): AdminFragment {
            var newAdminFragment = AdminFragment()
            return newAdminFragment
        }
    }

    override fun onResume() {
        super.onResume()

    }
}