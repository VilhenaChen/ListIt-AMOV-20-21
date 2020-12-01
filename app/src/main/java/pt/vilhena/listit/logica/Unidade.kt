package pt.vilhena.listit.logica

class Unidade {
    var designacao : String? = null
        get() = field
        set(value) {
            field = value
        }
    var abreviatura : String? = null
        get() = field
        set(value) {
            field = value
        }

    constructor(designacao: String, abreviatura: String){
        this.designacao = designacao
        this.abreviatura = abreviatura
    }



}