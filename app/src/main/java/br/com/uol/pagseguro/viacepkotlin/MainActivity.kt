package br.com.uol.pagseguro.viacepkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.uol.pagseguro.viacepkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ViewHome {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSend.setOnClickListener {
            showProgressBar()
            if (binding.etCep.text.toString().length == 8) {
//                val intent = Intent(this, RespActivity::class.java)
//                intent.putExtra(getString(R.string.cep_controller), binding.etCep.text.toString())
//                startActivity(intent)
            } else {
                showFailure(getString(R.string.error_cep_message))
            }
        }
    }

    override fun showProgressBar() {
        binding.pbCep.visibility = VISIBLE
    }

    override fun hidePorgressBar() {
        binding.pbCep.visibility = INVISIBLE
    }

    override fun showFailure(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}
