package br.com.uol.pagseguro.viacepkotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
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
    lateinit var btnClose: Button
    lateinit var tvResp: TextView
    lateinit var pbCep: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resp)
        btnClose = findViewById(R.id.btnClose)
        tvResp = findViewById(R.id.tvResp)
        pbCep = findViewById(R.id.pbCep)
        getCep(intent.getStringExtra("cep"))

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun getCep(cep: String?) {
        val urlCep = "https://viacep.com.br/ws/$cep/json/"
        doAsync {
            val url = URL(urlCep)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = 7000
            val content = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)
            var json = JSONObject(content)
            uiThread {
                ProgressIndication().tradeView(pbCep, tvResp)
                if (json.has("erro")) {
                    tvResp.text = "Erro no cep"
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

    class ProgressIndication {
        private fun fadeIn(view: View) {
            val animation = AlphaAnimation(0f, 1f)
            animation.duration = 500L
            view.startAnimation(animation)
        }

        private fun fadeOut(view: View) {
            val animation = AlphaAnimation(1f, 0f)
            animation.duration = 500L
            view.startAnimation(animation)
        }

        fun tradeView(view1: View, view2: View) {
            fadeOut(view1)
            Handler(Looper.getMainLooper()).postDelayed({
                view1.visibility = View.INVISIBLE
                view2.visibility = View.VISIBLE
                fadeIn(view2)
            }, 500L)
        }
    }
}