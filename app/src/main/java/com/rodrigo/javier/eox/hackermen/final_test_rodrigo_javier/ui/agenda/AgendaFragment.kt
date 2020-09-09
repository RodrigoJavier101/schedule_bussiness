package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.admin_.AdminFragment

class AgendaFragment : Fragment() {

    private lateinit var slideshowViewModel: AgendaViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(AgendaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_agenda, container, false)
        val textView: TextView = root.findViewById(R.id.text_agenda)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    companion object {
        fun newInstance(): AgendaFragment {
            var newAgendaFragment = AgendaFragment()
            return newAgendaFragment
        }
    }
}