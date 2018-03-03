package com.fiap.rm45401.demoaacretrofit.ui.mainscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.fiap.rm45401.demoaacretrofit.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btBuscarEndereco.setOnClickListener {
            mainViewModel.pesquisarEndereco(etCep.text.toString())
        }

        mainViewModel.apiResponse.observe(this, Observer { apiResponse ->
            if (apiResponse?.erro == null) {
                Log.i("TAG", "SUCESSO")
                tvEndereco.text = apiResponse?.endereco?.logradouro.toString()
                tvComplemento.text = apiResponse?.endereco?.complemento.toString()
                tvBairro.text = apiResponse?.endereco?.bairro.toString()
                tvEstado.text = apiResponse?.endereco?.uf.toString()
                tvCidade.text = apiResponse?.endereco?.localidade.toString()
            }
            else {
                Log.i("TAG", "ERRO: ${apiResponse.erro}")
            }
        })

        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading!!) {
                loading.visibility = View.VISIBLE
            }
            else {
                loading.visibility = View.GONE
            }
        })
    }
}
