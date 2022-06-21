package br.com.uol.pagseguro.viacepkotlin.model

import java.io.Serializable

data class AddressResponse(
    val cep: String?,
    val logradouro: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
): Serializable