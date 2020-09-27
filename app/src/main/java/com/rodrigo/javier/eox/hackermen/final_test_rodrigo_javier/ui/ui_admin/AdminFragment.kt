package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin

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
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.adapters.AdminViewPagerAdapter

class AdminFragment : Fragment() {

    var view_pager_adapter: FragmentPagerAdapter? = null
    private var tab_layout_admin: TabLayout? = null
    private var view_pager_admin: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin, container, false)
        initFragmentsAdmin(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        tab_layout_admin.setupWithViewPager(view_pager_admin)
    }

    private fun initFragmentsAdmin(view: View) {
        tab_layout_admin = view.findViewById(R.id.tab_layout_admin) as TabLayout
        view_pager_admin = view.findViewById<ViewPager2>(R.id.view_pager_admin) as ViewPager2
        view_pager_admin!!.adapter = (
                AdminViewPagerAdapter(
                    requireActivity().supportFragmentManager,
                    lifecycle
                )
                )

        TabLayoutMediator(
            tab_layout_admin!!,
            view_pager_admin!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Estadisticas"
                    1 -> tab.text = "Clientes"
                    2 -> tab.text = "Proveedores"
                }
            }).attach()

    }

    companion object {
        fun newInstance(): AdminFragment {
            var newAdminFragment = AdminFragment()
            return newAdminFragment
        }
    }

    /*private inner class AdminViewPagerAdapter(
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
    }*/
}