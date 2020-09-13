package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.admin_fragments.Admin_Clientes_Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.admin_fragments.Admin_Estadisticas_Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.admin_fragments.Admin_Proveedores_Fragment
import kotlinx.android.synthetic.main.fragment_admin.*

class AdminFragment : Fragment() {

    private lateinit var adminViewModel: AdminViewModel
    var view_pager_adapter: FragmentPagerAdapter? = null
    private var tab_layout_admin: TabLayout? = null
    private var view_pager_admin: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin, container, false)
        adminViewModel =
            ViewModelProviders.of(this).get(AdminViewModel::class.java)

        adminViewModel.liveData.observe(viewLifecycleOwner, Observer {

        })
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
//        view_pager_adapter = AdminViewPagerAdapter(requireActivity().supportFragmentManager)

        /* (view_pager_adapter as AdminViewPagerAdapter).addFragment(
             Admin_Estadisticas_Fragment.newInstance(),
             "Estadísticas"
         )

         (view_pager_adapter as AdminViewPagerAdapter).addFragment(
             Admin_Clientes_Fragment.newInstance(),
             "Clientes"
         )
         (view_pager_adapter as AdminViewPagerAdapter).addFragment(
             Admin_Proveedores_Fragment.newInstance(),
             "Proveedores"
         )*/

        TabLayoutMediator(
            tab_layout_admin!!,
            view_pager_admin!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Estadisticas_VP"
                    1 -> tab.text = "Clientes_VP"
//                    2 -> tab.text = "Proveedores_VP"
                }
            }).attach()

//        var view_pager_admin = view.findViewById<ViewPager>(R.id.view_pager_admin)
//        view_pager_admin.adapter = view_pager_adapter

    }

    companion object {
        fun newInstance(): AdminFragment {
            var newAdminFragment = AdminFragment()
            return newAdminFragment
        }
    }

    private inner class AdminViewPagerAdapter(fm: FragmentManager?, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm!!, lifecycle) {
        private val int_items = 5
        override fun createFragment(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = Admin_Estadisticas_Fragment.newInstance()//ViewPagerFragment()
                1 -> fragment = Admin_Clientes_Fragment.newInstance()//ViewPagerFragment()
//                2 -> fragment = Admin_Proveedores_Fragment.newInstance()//ViewPagerFragment()
//                3 -> fragment = ViewPagerFragment()
//                4 -> fragment = ViewPagerFragment()
            }
            return fragment!!
        }

        override fun getItemCount(): Int {
            return int_items
        }
    }
}