package br.com.uol.pagseguro.viacepkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class RespActivity : AppCompatActivity() {
    private lateinit var btnClose: Button
    private lateinit var tvResp: TextView
    private lateinit var pbCep: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resp)
        btnClose = findViewById(R.id.btnClose)
        tvResp = findViewById(R.id.tvResp)
        pbCep = findViewById(R.id.pbCep)
        getCep(intent.getStringExtra(getString(R.string.cep_controller)))

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun getCep(cep: String?) {
        val urlCep = "${getString(R.string.cep_controller)}$cep/json/"
        doAsync {
            val url = URL(urlCep)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = 7000
            val content = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)
            var json = JSONObject(content)
            uiThread {
                ProgressIndicator().tradeView(pbCep, tvResp)
                if (json.has(getString(R.string.error))) {
                    tvResp.text = getString(R.string.error_cep)
                } else {
                    val cep = json.getString("cep")
                    val logradouro = json.getString("logradouro")
                    val bairro = json.getString("bairro")
                    val cidade = json.getString("localidade")
                    val estado = json.getString("uf")
                    tvResp.text =
                        "CEP: $cep\nLOGRADOURO: $logradouro\nBAIRRO: $bairro\nCIDADE: $cidade\nESTADO: $estado"
                }
            }
        }
    }
}