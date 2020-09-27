package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database.User_Entity
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.interfaces.ItemUserClickListener
import kotlinx.android.synthetic.main.item_user.view.*


class HomeAdapter(
    var lista: List<User_Entity>
    ,
    private val listenerUpdateUser: ItemUserClickListener
) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    inner class MyViewHolder(var item_view: View) : RecyclerView.ViewHolder(item_view) {
        //        var lbl_id: TextView
        var lbl_nombre: TextView
        var lbl_password: TextView

        init {
//            lbl_id = item_view.findViewById(R.id.lbl_item_id_user)
            lbl_nombre = item_view.findViewById(R.id.lbl_item_nombre_user)
            lbl_password = item_view.findViewById(R.id.lbl_item_password_user)
        }

        fun initialize(
            action: ItemUserClickListener
        ) {
            item_view.setOnClickListener {
                listenerUpdateUser.itemUserUpdateClick(
                    user = lista.get(this.layoutPosition)
                )
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data: User_Entity = lista[position]
//        holder.item_view.lbl_item_id_user.setText(data.id_user.toString())
        holder.item_view.lbl_item_nombre_user.setText(data.user_name)
        holder.item_view.lbl_item_password_user.setText(data.password.toString())

        holder.initialize(
            listenerUpdateUser
        )
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun addItem(user: User_Entity?) {
        if (user != null) {
//            lista.add(user)
        }
        notifyDataSetChanged()
    }

}