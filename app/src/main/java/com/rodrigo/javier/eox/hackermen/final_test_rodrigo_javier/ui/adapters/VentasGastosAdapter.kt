package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.gastos.GastosFragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos.ventas.VentasFragment

/**
 * ADAPTER ESPECIAL PARA EL VIEW PAGER VENTAS/GASTOS*/
class VentasGastosAdapter(
    fragmentManager: FragmentManager?,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager!!, lifecycle) {
    private val int_items = 2
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = VentasFragment.newInstance()
            1 -> fragment = GastosFragment.newInstance()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return int_items
    }
}