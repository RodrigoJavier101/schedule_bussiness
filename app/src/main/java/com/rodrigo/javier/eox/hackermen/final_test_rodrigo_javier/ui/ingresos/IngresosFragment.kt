package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ingresos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class IngresosFragment : Fragment() {

    private lateinit var galleryViewModel: IngresosViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(IngresosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ingresos, container, false)
        val textView: TextView = root.findViewById(R.id.text_ingresos)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}