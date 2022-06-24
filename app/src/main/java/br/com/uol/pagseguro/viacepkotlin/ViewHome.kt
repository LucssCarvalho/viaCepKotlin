package br.com.uol.pagseguro.viacepkotlin

import br.com.uol.pagseguro.viacepkotlin.model.AddressResponse

interface ViewHome {

    fun showProgressBar()

    fun hideProgressBar()

    fun showFailure(message: String)

    fun startResponseActivity(address: AddressResponse)
}