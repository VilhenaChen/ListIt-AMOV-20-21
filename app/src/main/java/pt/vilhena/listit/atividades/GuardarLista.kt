package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_guardar_lista.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class GuardarLista : Activity() {

    lateinit var dados : Dados
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardar_lista)

        dados=intent.getSerializableExtra("dados") as Dados
    }

    fun onClickBtnSave(view: View) {
        if(editNomeLista.text.isEmpty()){
            Toast.makeText(this, "Indique um nome para a Lista", Toast.LENGTH_LONG).show()
            return
        }
        dados.getArrayListas().last().setNome(editNomeLista.text.toString())
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("dados", dados)
        Toast.makeText(this, "Lista ${editNomeLista.text} guardada com sucesso", Toast.LENGTH_LONG).show()
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}