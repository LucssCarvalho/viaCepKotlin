package br.com.uol.pagseguro.viacepkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btnSend: Button
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend = findViewById(R.id.btnSend)
        editText = findViewById(R.id.etCep)


        btnSend.setOnClickListener {
            if (editText.text.toString().length == 8) {
                val intent = Intent(this, RespActivity::class.java)
                intent.putExtra("cep", editText.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "o cep deve conter 8 numeros", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
