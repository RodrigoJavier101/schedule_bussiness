package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.ui.ui_admin.fragmentos_interiores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.R
class Admin_Estadisticas_Fragment : Fragment() {

    lateinit var lblTotalVentas: TextView
//    lateinit var dao: GestionDao
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_admin_estadisticas, container, false)
        lblTotalVentas = view.findViewById(R.id.lbl_total_ventas_estatisticas)
//        dao = RoomApplication.gestionDatabase.getGestionDao()
//        var lista_ventas: List<Ventas_Entity> = listOf()
//        CoroutineScope(Dispatchers.IO).launch {
//            lista_ventas = dao.getAllFromVentasTable()
//        }
//        Toast.makeText(context, lista_ventas.size.toString(), Toast.LENGTH_SHORT).show()

        /*var counter: Int = 0
        lista_ventas.forEach {
            counter += it.valor_producto_vendido
        }*/

//        lblTotalVentas.text = "Total ventas = ${counter.toString()}"
        return view
    }

    companion object {
        fun newInstance(): Admin_Estadisticas_Fragment {
            var newAdmin_Estaditicas_Fragment = Admin_Estadisticas_Fragment()
            return newAdmin_Estaditicas_Fragment
        }
    }

}
