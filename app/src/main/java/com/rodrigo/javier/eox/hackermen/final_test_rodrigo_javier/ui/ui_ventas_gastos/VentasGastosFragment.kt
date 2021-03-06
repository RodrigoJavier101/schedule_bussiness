package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.fragmentos.ventas_gastos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ventas_gastos.VentasGastosAdapter


class VentasGastosFragment : Fragment() {

    var adapter: FragmentPagerAdapter? = null
    private var tab_layout_ventas_gastos: TabLayout? = null
    private var view_pager_ventas_gastos: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ventas_gastos, container, false)

        initFragmentsAdmin(view)
        return view
    }

    private fun initFragmentsAdmin(view: View) {
        tab_layout_ventas_gastos = view.findViewById(R.id.tab_layout_ventas_gastos) as TabLayout
        view_pager_ventas_gastos =
            view.findViewById<ViewPager2>(R.id.view_pager_ventas_gastos) as ViewPager2
        view_pager_ventas_gastos!!.adapter = (
                VentasGastosAdapter(
                    requireActivity().supportFragmentManager,
                    lifecycle
                )
                )

        TabLayoutMediator(
            tab_layout_ventas_gastos!!,
            view_pager_ventas_gastos!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Ventas"
                    1 -> tab.text = "Gastos"
                }
            }).attach()
    }

    companion object {
        fun newInstance(): VentasGastosFragment {
            var newVentasGastosFragment = VentasGastosFragment()
            return newVentasGastosFragment
        }
    }
}