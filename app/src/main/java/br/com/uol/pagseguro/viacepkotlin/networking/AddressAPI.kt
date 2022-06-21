package br.com.uol.pagseguro.viacepkotlin.networking

import br.com.uol.pagseguro.viacepkotlin.model.AddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressAPI {

    @GET("/json/")
    suspend fun getAddress(
        @Query("cep")
        cep: String,
    ): Response<AddressResponse>
}