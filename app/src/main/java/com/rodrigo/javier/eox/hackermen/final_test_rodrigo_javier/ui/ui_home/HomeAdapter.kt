package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity
import java.util.ArrayList

class HomeAdapter (
    private var users: List<User_Entity>? = ArrayList()
) :
    RecyclerView.Adapter<HomeAdapter.ClientesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ClientesHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ClientesHolder, position: Int) {
        var dataUser = users!![position]
        holder.textViewNombreUser.text = dataUser.user_name
        holder.textViewPassUser.text = dataUser.password.toString()
    }


    override fun getItemCount(): Int {
        return users!!.size
    }

    fun setUsers(users: List<User_Entity>?) {
        this.users = users
        notifyDataSetChanged()
    }

    fun getUserAt(position: Int): User_Entity? {
        return users!![position]
    }

    inner class ClientesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombreUser: TextView
        val textViewPassUser: TextView

        init {
            textViewNombreUser = itemView.findViewById(R.id.lbl_item_nombre_user)
            textViewPassUser = itemView.findViewById(R.id.lbl_item_password_user)
        }
    }

}
