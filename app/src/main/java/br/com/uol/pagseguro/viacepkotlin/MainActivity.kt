package br.com.uol.pagseguro.viacepkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.uol.pagseguro.viacepkotlin.data.AddressData
import br.com.uol.pagseguro.viacepkotlin.databinding.ActivityMainBinding
import br.com.uol.pagseguro.viacepkotlin.model.AddressResponse
import br.com.uol.pagseguro.viacepkotlin.presenter.CepPresenter

class MainActivity : AppCompatActivity(), ViewHome {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: CepPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSend.setOnClickListener {
            showProgressBar()
            if (binding.etCep.text.toString().length == 8) {
                val dataSource = AddressData()
                presenter = CepPresenter(this, dataSource)
                startResponseActivity();

            } else {
                showFailure(getString(R.string.error_cep_message))
            }
        }
    }

    private fun search() {

    }

    override fun showProgressBar() {
        binding.pbCep.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        binding.pbCep.visibility = INVISIBLE
    }

    override fun showFailure(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun startResponseActivity(address: AddressResponse) {
        TODO("Not yet implemented")
    }

}
