package pt.vilhena.listit.logica

import android.util.Log
import pt.vilhena.listit.atividades.ABS
import java.io.Serializable

class Dados : Serializable{

    private var listaUnidades = ArrayList<Unidade>()
    private var listaProdutos = ArrayList<Produto>()
    private var listaProdutosPesquisados = ArrayList<Produto>()

    //Unidades

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

    //Produtos

    fun addProduto(designacao : String, marca : String, quantidade : Int, unidade : String, categoria : String, notas : String) {
        listaProdutos.add(Produto(designacao, marca, quantidade, unidade, categoria, notas))
    }

    fun removeProduto(designacao: String){
        for (i in listaProdutos.indices){
            if(listaProdutos[i].designacao == designacao)
            {
                listaProdutos.removeAt(i)
            }
        }
    }

    fun getListaProdutos() : ArrayList<Produto>
    {
        return listaProdutos
    }

    fun adicionaProdutosPesquisadosPorDesignacao(designacao: String) : Boolean{
        var encontrei : Boolean = false
        for(i in listaProdutos.indices)
        {
            if(listaProdutos[i].designacao == designacao)
            {
                listaProdutosPesquisados.add(listaProdutos[i])
                encontrei = true
            }
        }
        return encontrei
    }
    fun adicionaProdutosPesquisadosPorMarca(marca: String) : Boolean{
        var encontrei : Boolean = false
        for(i in listaProdutos.indices)
        {
            if(listaProdutos[i].marca== marca)
            {
                listaProdutosPesquisados.add(listaProdutos[i])
                encontrei = true
            }
        }
        return encontrei
    }
    fun adicionaProdutosPesquisadosPorCategoria(categoria: String) : Boolean{
        var encontrei : Boolean = false
        for(i in listaProdutos.indices)
        {
            if(listaProdutos[i].categoria == categoria)
            {
                listaProdutosPesquisados.add(listaProdutos[i])
                encontrei = true
            }
        }
        return encontrei
    }

    //Produtos Pesquisados

    fun addProdutoPesquisado(produto : Produto) {
        listaProdutosPesquisados.add(produto)
    }

    fun removeProdutosPesquisados(){
        listaProdutosPesquisados.clear()
    }

    fun getListaProdutosPesquisados() : ArrayList<Produto>
    {
        return listaProdutosPesquisados
    }


}