package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.PRUEBA_ROOM_EJERCICIO

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R

class RecommendationsAdapter(
    private var recommendationsList: MutableList<RecommendationDataView>,
    var context: Context
) : RecyclerView.Adapter<RecommendationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecommendationViewHolder {
        return RecommendationViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.recommendation_item_fake, parent, false)
        )
    }

    override fun getItemCount(): Int = recommendationsList.size
    override fun onBindViewHolder(
        holder: RecommendationViewHolder, position:
        Int
    ) {
        val data = recommendationsList[position]
        holder.recommendationCheck.text = data.recommendation.toString()
    }

    fun updateData(items: List<RecommendationDataView>) {
//hace update de la data cuando el usuario agrega nuevas recomendaciones
        recommendationsList.clear()
        recommendationsList.addAll(items)
        notifyDataSetChanged()
    }
}

class RecommendationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val recommendationCheck = view.findViewById<TextView>(R.id.recommendation_input)
}