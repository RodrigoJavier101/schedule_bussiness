package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.agenda

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.PRUEBA_ROOM_EJERCICIO.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import kotlinx.android.synthetic.main.add_recommendation_layout_fake.*
import kotlinx.android.synthetic.main.add_recommendation_layout_fake.view.*
import kotlinx.android.synthetic.main.content_main_fake.*
import kotlinx.android.synthetic.main.fragment_agenda_fake.*

class AgendaFragment : Fragment() {

    private lateinit var agendaViewModel: AgendaViewModel

    /*FAKE+++++++++++++++++++++++++++++++++++++++++++*/
    private lateinit var list: RecyclerView
    private lateinit var adapter: RecommendationsAdapter
    private lateinit var dataBase: RecommendationsDatabase
    private lateinit var dao: RecommendationsDAO
    private lateinit var addButton: FloatingActionButton

    /*+++++++++++++++++++++++++++++++++++++++++++++++++*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agendaViewModel =
            ViewModelProviders.of(this).get(AgendaViewModel::class.java)
        val view = inflater.inflate(R.layout.content_main_fake, container, false)
//        val textView: TextView = view.findViewById(R.id.text_agenda)
        agendaViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })


//        setSupportActionBar(toolbar)
        setUpViews(view)
        dataBase = RoomApplicationFAKE.recommendationsDatabase!!
        dao = dataBase.getRecommendationsDAO()
        setUpAddButton()



        return view
    }


    private fun setUpViews(view:View) {
        list = view.findViewById(R.id.rv_recommendations_list)
        list.layoutManager = LinearLayoutManager(context)
        addButton = fab
        adapter = RecommendationsAdapter(mutableListOf(), requireContext())
        list.adapter = adapter
    }


    private fun setUpAddButton() {
        addButton.setOnClickListener {
            val dialogView = layoutInflater
                .inflate(R.layout.add_recommendation_layout_fake, null)
            val recommendationText = dialogView.recommendation_input
            val dialogBuilder = AlertDialog
                .Builder(requireContext())
                .setTitle("Agrega una recomendación")
                .setView(dialogView)
                .setNegativeButton("Cerrar") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                .setPositiveButton("Agregar") { dialog: DialogInterface, _: Int ->
                    if (recommendationText.text?.isNotEmpty()!!) {
                        AsyncTask.execute {
                            dao.insertRecommendations(createEntity(recommendation_input.text.toString()) )
                            val newItems = createEntityListFromDatabase(dao.getAllRecommendations())
//                            runOnUiThread {
//                                adapter.updateData(newItems)
//                                dialog.dismiss()
//                            }
                        }
                    }
                }
            dialogBuilder.create().show()
        }
    }


    private fun createEntity(text: String): RecommendationEntity {
        return RecommendationEntity(recommendation = text, isCheck = false, id = 0)
    }

    private fun createEntityListFromDatabase(entities: List<RecommendationEntity>):
            MutableList<RecommendationDataView> {
        val dataList = mutableListOf<RecommendationDataView>()
        if (entities.isNotEmpty()) {
            for (entity in entities) {
                val dataView = RecommendationDataView(
                    entity.id,
                    entity.recommendation.toString(),
                    entity.isCheck
                )
                dataList.add(dataView)
            }
        }
        return dataList
    }


    companion object {
        fun newInstance(): AgendaFragment {
            var newAgendaFragment = AgendaFragment()
            return newAgendaFragment
        }
    }
}