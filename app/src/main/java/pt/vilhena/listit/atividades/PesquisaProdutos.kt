package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_nova_unidade.*
import kotlinx.android.synthetic.main.activity_pesquisa_produtos.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados


class PesquisaProdutos : Activity() {
    lateinit var dados : Dados
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_produtos)

        dados = intent.getSerializableExtra("dados") as Dados

    }

    fun OnClickBtnSearch(view: View) {
        if(editDesignacaoAPesquisar.text.isEmpty() && editMarcaAPesquisar.text.isEmpty() && editCategoriaAPesquisar.text.isEmpty()){
            Toast.makeText(this, R.string.FaltaCampos, Toast.LENGTH_LONG).show()
            return
        }
        if( (!editDesignacaoAPesquisar.text.isEmpty()) ){
                if( (!editMarcaAPesquisar.text.isEmpty()) || (!editCategoriaAPesquisar.text.isEmpty()) ) {
                    Toast.makeText(this, R.string.CamposAMais, Toast.LENGTH_LONG).show()
                    return
                }
                else
                {
                    if(!dados.adicionaProdutosPesquisadosPorDesignacao(editDesignacaoAPesquisar.text.toString().capitalize()))
                    {
                        Toast.makeText(this, R.string.NaoProdutoDesignacao, Toast.LENGTH_LONG).show()
                        return
                    }
                }
        }
        else
        {
            if((!editMarcaAPesquisar.text.isEmpty()) && (!editCategoriaAPesquisar.text.isEmpty()))
            {
                Toast.makeText(this, R.string.FaltaCampos, Toast.LENGTH_LONG).show()
                return
            }
            else
            {
                if(!editMarcaAPesquisar.text.isEmpty())
                {
                    if(!dados.adicionaProdutosPesquisadosPorMarca(editMarcaAPesquisar.text.toString().capitalize()))
                    {
                        Toast.makeText(this, R.string.NaoProdutoMarca, Toast.LENGTH_LONG).show()
                        return
                    }
                }
                else
                {
                    if(!dados.adicionaProdutosPesquisadosPorCategoria(editCategoriaAPesquisar.text.toString().capitalize()))
                    {
                        Toast.makeText(this, R.string.NaoProdutoCategoria, Toast.LENGTH_LONG).show()
                        return
                    }
                }
            }
        }
        val intent = Intent(this, VerProdutosPesquisados::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerProdutos::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }


}