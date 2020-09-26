package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_ruta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RutaContainerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var adapter: FragmentPagerAdapter? = null
    private var tab_layout_ventas_gastos: TabLayout? = null
    private var view_pager_ventas_gastos: ViewPager2? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_ruta_container, container, false)
        initFragmentsAdmin(view)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RutaContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initFragmentsAdmin(view: View) {
        tab_layout_ventas_gastos = view.findViewById(R.id.tab_layout_ventas_gastos) as TabLayout
        view_pager_ventas_gastos =
            view.findViewById<ViewPager2>(R.id.view_pager_ventas_gastos) as ViewPager2
        view_pager_ventas_gastos!!.adapter = (
                RutaContainerAdapter(
                    requireActivity().supportFragmentManager,
                    lifecycle
                )
                )

        TabLayoutMediator(
            tab_layout_ventas_gastos!!,
            view_pager_ventas_gastos!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Ruta"
                    1 -> tab.text = "ADD"
                }
            }).attach()
    }

}