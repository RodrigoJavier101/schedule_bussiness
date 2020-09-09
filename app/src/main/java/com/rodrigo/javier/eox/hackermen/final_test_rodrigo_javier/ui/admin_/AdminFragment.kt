package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class AdminFragment : Fragment() {

    private lateinit var slideshowViewModel: AdminViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(AdminViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_admin, container, false)
        val textView: TextView = root.findViewById(R.id.text_admin)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}