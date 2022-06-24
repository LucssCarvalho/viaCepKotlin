package br.com.uol.pagseguro.viacepkotlin.presenter

import br.com.uol.pagseguro.viacepkotlin.ViewHome
import br.com.uol.pagseguro.viacepkotlin.data.AddressData
import br.com.uol.pagseguro.viacepkotlin.model.AddressResponse

class CepPresenter(
    private val view: ViewHome,
    private val dataSource: AddressData
) : CepHome.Presenter {

    override fun search(cep: String) {
        this.view.showProgressBar()
        this.dataSource.getAddress(cep, this)
    }

    override fun onSuccess(addressResponse: AddressResponse) {
        this.view.startResponseActivity(addressResponse)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

}