package com.fiap.rm45401.demoaacretrofit.repositories

import android.arch.lifecycle.LiveData
import com.fiap.rm45401.demoaacretrofit.entities.EnderecoResponse

/**
 * Created by logonrm on 03/03/2018.
 */

interface EnderecoRepository {
    fun buscarEndereco(cep: String): LiveData<EnderecoResponse>
}