package br.com.uol.pagseguro.viacepkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.uol.pagseguro.viacepkotlin.databinding.ActivityRespBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class RespActivity : AppCompatActivity() {

    lateinit var binding: ActivityRespBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRespBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getCep(intent.getStringExtra(getString(R.string.cep_controller)))

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun getCep(cep: String?) {
        val urlCep = "${getString(R.string.cep_controller)}$cep"
        doAsync {
            val url = URL(urlCep)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = 7000
            val content = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)
            var json = JSONObject(content)
            uiThread {
                ProgressIndicator().tradeView(binding.pbCep, binding.tvResp)
                if (json.has(getString(R.string.error))) {
                    binding.tvResp.text = getString(R.string.error_cep)
                } else {
                    val cep = json.getString("cep")
                    val logradouro = json.getString("logradouro")
                    val bairro = json.getString("bairro")
                    val cidade = json.getString("localidade")
                    val estado = json.getString("uf")
                    binding.tvResp.text =
                        "CEP: $cep\nLOGRADOURO: $logradouro\nBAIRRO: $bairro\nCIDADE: $cidade\nESTADO: $estado"
                }
            }
        }
    }
}