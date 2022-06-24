package br.com.uol.pagseguro.viacepkotlin.presenter

import br.com.uol.pagseguro.viacepkotlin.model.AddressResponse

interface CepHome {
    interface Presenter {
        fun search(cep: String)

        fun onSuccess(addressResponse: AddressResponse)

        fun onError(message: String)

        fun onComplete()
    }
}