package pt.vilhena.listit.logica

import java.io.Serializable


class Dados : Serializable{

    private var listaUnidades = ArrayList<Unidade>()
    private var listaProdutos = ArrayList<Produto>()
    private var listaProdutosPesquisados = ArrayList<Produto>()
    private var listaProdutosOrdenados = ArrayList<Produto>()
    private var arrayListas = ArrayList<Lista>()
    val ORDENA_POR_DESIGNACAO = 0
    val ORDENA_POR_QUANTIDADE = 1
    val ORDENA_POR_PRECO = 2
    val ORDEM_CRESCENTE = 0
    val ORDEM_DECRESCENTE = 1

    //Unidades

    fun addUnidade(designacao: String, abreviatura: String){
        listaUnidades.add(Unidade(designacao,abreviatura))
    }

    fun removeUnidade(designacao: String){
        for (i in listaUnidades.indices){
            if(listaUnidades[i].designacao == designacao)
            {
                listaUnidades.removeAt(i)
                return
            }
        }
    }

    fun getListaUnidades() : ArrayList<Unidade>
    {
        return listaUnidades
    }

    //Produtos

    fun getProduto(designacao : String, marca : String, quantidade : Int, unidade : String, categoria : String, notas : String, preco : Float) : Int{
        for(i in listaProdutos.indices) {
            if(listaProdutos[i].designacao == designacao && listaProdutos[i].marca == marca
                && listaProdutos[i].quantidade == quantidade && listaProdutos[i].unidade == unidade
                && listaProdutos[i].categoria == categoria && listaProdutos[i].notas == notas
                && listaProdutos[i].preco == preco) {
                    return i
            }
        }
        return 0
   }
    
    fun addProduto(designacao : String, marca : String, quantidade : Int, unidade : String, categoria : String, notas : String, preco : Float) {
        listaProdutos.add(Produto(designacao, marca, quantidade, unidade, categoria, notas,preco))
    }

    fun removeProduto(designacao: String){
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


    //Produtos Ordenados

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

    fun removeProdutosOrdenados(){
        listaProdutosOrdenados.clear()
    }

    fun getListaProdutosOrdenados() : ArrayList<Produto>
    {
        return listaProdutosOrdenados
    }

    fun fazCopiaArrayProdutosParaOrdenar()
    {
        for (i in listaProdutos.indices)
        {
            listaProdutosOrdenados.add(listaProdutos[i])
        }
    }

    //Listas

    fun addLista() {
        arrayListas.add(Lista())
    }

    fun removeLista(posicao : Int){
        arrayListas.removeAt(posicao)
    }

    fun removeListaComNome(nome: String) {
        for(i in arrayListas.indices)
        {
            if(arrayListas[i].getNome() == nome)
            {
                arrayListas.removeAt(i)
                return
            }
        }
    }

    fun getArrayListas() : ArrayList<Lista>
    {
        return arrayListas
    }

    fun adicionaProdutosPesquisadosPorDesignacaoLista(designacao: String) : Boolean{
        var encontrei : Boolean = false
        for(i in listaProdutos.indices)
        {
            if(listaProdutos[i].designacao == designacao)
            {
                arrayListas.last().addProdutoPesquisado(listaProdutos[i])
                encontrei = true
            }
        }
        return encontrei
    }
    fun adicionaProdutosPesquisadosPorMarcaLista(marca: String) : Boolean{
        var encontrei : Boolean = false
        for(i in listaProdutos.indices)
        {
            if(listaProdutos[i].marca== marca)
            {
                arrayListas.last().addProdutoPesquisado(listaProdutos[i])
                encontrei = true
            }
        }
        return encontrei
    }
    fun adicionaProdutosPesquisadosPorCategoriaLista(categoria: String) : Boolean{
        var encontrei : Boolean = false
        for(i in listaProdutos.indices)
        {
            if(listaProdutos[i].categoria == categoria)
            {
                arrayListas.last().addProdutoPesquisado(listaProdutos[i])
                encontrei = true
            }
        }
        return encontrei
    }

    fun fazCopiaLista(posicao : Int)
    {
        for(i in arrayListas[posicao].getListaProdutos().indices)
        {
            arrayListas.last().getListaProdutos().add(arrayListas[posicao].getListaProdutos()[i])
        }
    }




}