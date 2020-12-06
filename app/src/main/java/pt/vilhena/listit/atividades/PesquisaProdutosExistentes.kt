package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pesquisa_produtos.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class PesquisaProdutosExistentes : Activity() {
    lateinit var dados : Dados
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_produtos_existentes)

        dados = intent.getSerializableExtra("dados") as Dados
    }


    fun OnClickBtnSearch(view: View) {
        if(this.editDesignacaoAPesquisar.text.isEmpty() && this.editMarcaAPesquisar.text.isEmpty() && this.editCategoriaAPesquisar.text.isEmpty()){
            Toast.makeText(this, "Indique um dos campos", Toast.LENGTH_LONG).show()
            return
        }
        if( (!this.editDesignacaoAPesquisar.text.isEmpty()) ){
            if( (!editMarcaAPesquisar.text.isEmpty()) || (!this.editCategoriaAPesquisar.text.isEmpty()) ) {
                Toast.makeText(this, "Indique apenas um dos campos", Toast.LENGTH_LONG).show()
                return
            }
            else
            {
                if(!dados.adicionaProdutosPesquisadosPorDesignacaoLista(this.editDesignacaoAPesquisar.text.toString().capitalize()))
                {
                    Toast.makeText(this, "Não existe nenhum produto com a designação indicada", Toast.LENGTH_LONG).show()
                    return
                }
            }
        }
        else
        {
            if((!editMarcaAPesquisar.text.isEmpty()) && (!editCategoriaAPesquisar.text.isEmpty()))
            {
                Toast.makeText(this, "Indique apenas um dos campos", Toast.LENGTH_LONG).show()
                return
            }
            else
            {
                if(!editMarcaAPesquisar.text.isEmpty())
                {
                    if(!dados.adicionaProdutosPesquisadosPorMarcaLista(editMarcaAPesquisar.text.toString().capitalize()))
                    {
                        Toast.makeText(this, "Não existe nenhum produto com a marca indicada", Toast.LENGTH_LONG).show()
                        return
                    }
                }
                else
                {
                    if(!dados.adicionaProdutosPesquisadosPorCategoriaLista(editCategoriaAPesquisar.text.toString().capitalize()))
                    {
                        Toast.makeText(this, "Não existe nenhum produto com a categoria indicada", Toast.LENGTH_LONG).show()
                        return
                    }
                }
            }
        }
        val intent = Intent(this, ProdutoExistentePesquisado::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ProdutoExistente::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}