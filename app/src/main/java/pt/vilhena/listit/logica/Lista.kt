package pt.vilhena.listit.logica

import android.content.Intent
import pt.vilhena.listit.MainActivity
import java.io.Serializable

class Lista : Serializable{
    val ORDENA_POR_DESIGNACAO = 0
    val ORDENA_POR_QUANTIDADE = 1
    val ORDENA_POR_PRECO = 2
    val ORDEM_CRESCENTE = 0
    val ORDEM_DECRESCENTE = 1
    private var nome : String = ""

    private var listaProdutos = ArrayList<Produto>()
    private var listaProdutosPesquisados = ArrayList<Produto>()
    private var listaProdutosOrdenados = ArrayList<Produto>()

    fun addProduto(produto: Produto) {
        listaProdutos.add(produto)
    }

    fun removeProduto(designacao : String){
        for (i in listaProdutos.indices){
            if(listaProdutos[i].designacao == designacao)
            {
                listaProdutos.removeAt(i)
                return
            }
        }
    }

    fun getListaProdutos() : ArrayList<Produto>
    {
        return listaProdutos
    }

    fun getNome() : String{
        return nome
    }

    fun setNome(nome : String)
    {
        this.nome = nome
    }

    //Produtos Pesquisados

    fun addProdutoPesquisado(produto: Produto){
        listaProdutosPesquisados.add(produto)
    }

    fun removeProdutosPesquisados(){
        listaProdutosPesquisados.clear()
    }

    fun getListaProdutosPesquisados() : ArrayList<Produto>
    {
        return listaProdutosPesquisados
    }

    //Produtos Ordenados

    fun addProdutoOrdenados(produto: Produto){
        listaProdutosOrdenados.add(produto)
    }

    fun removeProdutosOrdenados(){
        listaProdutosOrdenados.clear()
    }

    fun getListaProdutosOrdenados() : ArrayList<Produto>
    {
        return listaProdutosOrdenados
    }

    fun ordenaProdutos(modo : Int, ordem: Int)
    {
        fazCopiaArrayProdutosParaOrdenar()
        for(i in listaProdutosOrdenados.indices)
        {
            listaProdutosOrdenados[i].modoOrdenacao = modo
        }
        if(ordem == ORDEM_CRESCENTE)
        {
            listaProdutosOrdenados.sort()
        }
        else
        {
            listaProdutosOrdenados.sortDescending()
        }
    }

    fun fazCopiaArrayProdutosParaOrdenar()
    {
        for (i in listaProdutos.indices)
        {
            listaProdutosOrdenados.add(listaProdutos[i])
        }
    }
}