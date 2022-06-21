package br.com.uol.pagseguro.viacepkotlin

interface ViewHome {

    fun showProgressBar()

    fun hidePorgressBar()

    fun showFailure(message: String)
}