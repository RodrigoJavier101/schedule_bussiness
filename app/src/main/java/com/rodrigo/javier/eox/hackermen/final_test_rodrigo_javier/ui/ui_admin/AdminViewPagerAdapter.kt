package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_clientes.AdminClientesFragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_estadisticas.AdminEstadisticasFragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores.admin_proveedores.AdminProveedoresFragment

class AdminViewPagerAdapter(
    fragmentManager: FragmentManager?,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager!!, lifecycle) {
    private val int_items = 3
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            2 -> fragment = AdminEstadisticasFragment.newInstance()
            0 -> fragment = AdminClientesFragment.newInstance()
            1 -> fragment = AdminProveedoresFragment.newInstance()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return int_items
    }
}