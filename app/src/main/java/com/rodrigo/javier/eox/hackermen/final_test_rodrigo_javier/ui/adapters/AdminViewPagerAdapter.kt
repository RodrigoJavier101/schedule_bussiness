package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.admin_.admin_fragments.Admin_Clientes_Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.admin_.admin_fragments.Admin_Estadisticas_Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.admin_.admin_fragments.Admin_Proveedores_Fragment


class AdminViewPagerAdapter(
    fragmentManager: FragmentManager?,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager!!, lifecycle) {
    private val int_items = 3
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Admin_Estadisticas_Fragment.newInstance()
            1 -> fragment = Admin_Clientes_Fragment.newInstance()
            2 -> fragment = Admin_Proveedores_Fragment.newInstance()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return int_items
    }
}