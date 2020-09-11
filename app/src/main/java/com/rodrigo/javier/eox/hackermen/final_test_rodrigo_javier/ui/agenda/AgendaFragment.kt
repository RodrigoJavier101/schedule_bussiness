package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.agenda

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.PRUEBA_ROOM_EJERCICIO.*
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import kotlinx.android.synthetic.main.add_recommendation_layout_fake.view.*


class AgendaFragment : Fragment() {

    private lateinit var agendaViewModel: AgendaViewModel

    /*FAKE+++++++++++++++++++++++++++++++++++++++++++*/
    private lateinit var list: RecyclerView
    private lateinit var adapter: RecommendationsAdapter
    private lateinit var dataBase: RecommendationsDatabase
    private lateinit var dao: RecommendationsDAO
    private lateinit var floatingActionButton: FloatingActionButton

    /*+++++++++++++++++++++++++++++++++++++++++++++++++*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agendaViewModel =
            ViewModelProviders.of(this).get(AgendaViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_agenda_fake, container, false)
//        val textView: TextView = view.findViewById(R.id.)
        agendaViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })


//        setSupportActionBar(toolbar)


        dataBase = RoomApplicationFAKE.recommendationsDatabase!!
        dao = dataBase.getRecommendationsDAO()
        setUpViews(view)
        setUpAddButton()

        return view
    }


    private fun setUpViews(view: View) {
        list = view.findViewById(R.id.rv_recommendations_list)
        list.layoutManager = LinearLayoutManager(context)
        floatingActionButton = view.findViewById(R.id.fab)
        adapter = RecommendationsAdapter(mutableListOf(), requireContext())
        list.adapter = adapter
    }


    private fun setUpAddButton() {
        floatingActionButton.setOnClickListener {

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
//                            var recomm =
//                                view?.findViewById<TextInputEditText>(R.id.recommendation_input)

                            if (recommendationText != null) {
                                dao.insertRecommendations(createEntity(recommendationText.text.toString()))

                            }
                            val newItems = createEntityListFromDatabase(dao.getAllRecommendations())
                            Log.d(":X:X:X:X:X:X:XX:X:.....", newItems.toString())

                            var thread = Thread() {
                                fun run() {
                                    try {
                                        synchronized(this) {
                                            Thread.sleep(300)
                                            runOnUiThread {
                                                adapter.updateData(newItems)

                                                dialog.dismiss()

                                            }
                                        }
                                    } catch (e: InterruptedException) {
                                        Log.d(
                                            ":X:X:X:X:X:X:XX:X:.....",
                                            e.printStackTrace().toString()
                                        )
                                    }
                                }
                            }
                            thread.start()

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
            MutableList<RecommendationEntity> {
        val dataList = mutableListOf<RecommendationEntity>()
        if (entities.isNotEmpty()) {
            for (entity in entities) {
                val dataView = RecommendationEntity(
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