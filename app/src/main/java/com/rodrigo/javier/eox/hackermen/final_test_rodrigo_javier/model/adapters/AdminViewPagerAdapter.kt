package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdminViewPagerAdapter(fragment_manager: FragmentManager) : FragmentPagerAdapter(fragment_manager) {

    private val fragment_list: MutableList<Fragment> = ArrayList()
    private val title_list: MutableList<String> = ArrayList()

    override fun getCount(): Int {
        return fragment_list.size
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    override fun getItem(position: Int): Fragment {
        return fragment_list[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragment_list.add(fragment)
        title_list.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title_list[position]
    }

}