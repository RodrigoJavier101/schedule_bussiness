package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class RutaContainerAdapter(
    fragmentManager: FragmentManager?,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager!!, lifecycle) {
    private val int_items = 2
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RutaFragment_.newInstance()
            1 -> fragment = AddClienteRutaFragment.newInstance()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return int_items
    }
}