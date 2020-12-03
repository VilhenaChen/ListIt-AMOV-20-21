package pt.vilhena.listit.logica

import android.util.Log
import pt.vilhena.listit.atividades.ABS
import java.io.Serializable

class Dados : Serializable{

    private var listaUnidades = ArrayList<Unidade>()

    fun addUnidade(designacao: String, abreviatura: String){
        listaUnidades.add(Unidade(designacao,abreviatura))
    }

    fun removeUnidade(designacao: String){
        for (i in listaUnidades.indices){
            if(listaUnidades[i].designacao == designacao)
            {
                listaUnidades.removeAt(i)
            }
        }
    }

    fun getListaUnidades() : ArrayList<Unidade>
    {
        return listaUnidades
    }
}