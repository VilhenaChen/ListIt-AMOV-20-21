package pt.vilhena.listit.logica

import java.io.Serializable

class Produto : Serializable {
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
    var quantidade : Int? = 0
        get() = field
        set(value) {
            field = value
        }
    var quantidadeObtida : Int? = 0
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

    constructor(designacao : String, marca : String, quantidade : Int, unidade : String, categoria : String, notas : String)
    {
        this.designacao = designacao
        this.marca = marca
        this.quantidade = quantidade
        this.unidade = unidade
        this.categoria = categoria
        this.notas = notas
        this.estado = "Em aberto"
    }
}