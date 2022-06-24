package br.com.uol.pagseguro.viacepkotlin.data

import br.com.uol.pagseguro.viacepkotlin.networking.RetrofitInstance
import br.com.uol.pagseguro.viacepkotlin.presenter.CepHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddressData {
    fun getAddress(cep: String, callback: CepHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getAddress(cep)
            if (response.isSuccessful) {
                response.body()?.let { newResponse ->
                    callback.onSuccess(newResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
}