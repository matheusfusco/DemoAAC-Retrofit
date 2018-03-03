package com.fiap.rm45401.demoaacretrofit.entities

/**
 * Created by logonrm on 03/03/2018.
 */

class EnderecoResponse {
    var endereco: Endereco?
    var erro: Throwable?


    constructor(endereco: Endereco?) {
        this.endereco = endereco
        this.erro = null
    }

    constructor(erro: Throwable) {
        this.endereco = null
        this.erro = erro
    }
}