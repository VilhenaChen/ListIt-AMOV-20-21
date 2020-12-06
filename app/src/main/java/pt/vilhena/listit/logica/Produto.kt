package pt.vilhena.listit.logica

import java.io.Serializable

class Produto : Serializable, Comparable<Produto> {
    val ORDENA_POR_DESIGNACAO = 0
    val ORDENA_POR_QUANTIDADE = 1
    val ORDENA_POR_PRECO = 2

    var designacao : String? = null
        get() = field
        set(value) {
            field = value
        }
    var marca : String? = null
        get() = field
        set(value) {
            field = value
        }
    var quantidade : Int = 0
        get() = field
        set(value) {
            field = value
        }
    var quantidadeObtida : Int = 0
        get() = field
        set(value) {
            field = value
        }
    var unidade : String? = null
        get() = field
        set(value) {
            field = value
        }
    var categoria : String? = null
        get() = field
        set(value) {
            field = value
        }
    var notas : String? = null
        get() = field
        set(value) {
            field = value
        }
    var estado : String? = null
        get() = field
        set(value) {
            field = value
        }

    var preco : Float = 0F
        get() = field
        set(value) {
            field = value
        }

    var modoOrdenacao = 0
        get() = field
        set(value) {
            field = value
        }

    constructor(designacao : String, marca : String, quantidade : Int, unidade : String, categoria : String, notas : String, preco : Float)
    {
        this.designacao = designacao
        this.marca = marca
        this.quantidade = quantidade
        this.unidade = unidade
        this.categoria = categoria
        this.notas = notas
        this.estado = "Em aberto"
        this.preco = preco
    }

    override fun compareTo(other: Produto): Int {
        if(modoOrdenacao == ORDENA_POR_DESIGNACAO)
        {
            return this.designacao?.compareTo(other.designacao.toString())!!
        }
        else
        {
            if(modoOrdenacao == ORDENA_POR_QUANTIDADE)
            {
                return this.quantidade.compareTo(other.quantidade)
            }
            else
            {
                return this.preco.compareTo(other.preco)
            }
        }
    }
}