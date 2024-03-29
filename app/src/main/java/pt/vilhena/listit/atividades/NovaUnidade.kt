package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nova_unidade.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class NovaUnidade : Activity() {
    lateinit var dados : Dados
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_unidade)

        dados=intent.getSerializableExtra("dados") as Dados
    }

    fun onClickBtnAdd(view: View) {
        if(editDesignacao.text.isEmpty()){
            Toast.makeText(this, R.string.DesignacaoFalta, Toast.LENGTH_LONG).show()
            return
        }
        if (editAbreviatura.text.isEmpty()) {
            Toast.makeText(this, R.string.AbreviaturaFalta, Toast.LENGTH_LONG).show()
            return
        }

        dados.addUnidade(editDesignacao.text.toString().capitalize(), editAbreviatura.text.toString().capitalize())

        val intent = Intent(this, VerUnidades::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
        return
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("DADOS", dados)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        dados=savedInstanceState.getSerializable("DADOS") as Dados
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerUnidades::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}
