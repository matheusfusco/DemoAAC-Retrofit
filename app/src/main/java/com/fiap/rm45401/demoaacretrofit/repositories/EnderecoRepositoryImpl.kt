package com.fiap.rm45401.demoaacretrofit.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.fiap.rm45401.demoaacretrofit.api.EnderecoAPI
import com.fiap.rm45401.demoaacretrofit.entities.Endereco
import com.fiap.rm45401.demoaacretrofit.entities.EnderecoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by logonrm on 03/03/2018.
 */

class EnderecoRepositoryImpl : EnderecoRepository {

    private val enderecoAPI : EnderecoAPI

    init {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://viacep.com.br")
                .build()

        enderecoAPI = retrofit.create(EnderecoAPI::class.java)
    }

    override fun buscarEndereco(cep: String): LiveData<EnderecoResponse> {
        val liveData = MutableLiveData<EnderecoResponse>()
        enderecoAPI.pesquisar(cep).enqueue(object : Callback<Endereco> {
            override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                liveData.value = EnderecoResponse(t!!)
            }

            override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {
                liveData.value = EnderecoResponse(response?.body())
            }
        })

        return liveData
    }
}