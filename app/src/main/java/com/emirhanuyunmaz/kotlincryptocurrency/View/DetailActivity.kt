package com.emirhanuyunmaz.kotlincryptocurrency.View

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CryptoDetailModel
import com.emirhanuyunmaz.kotlincryptocurrency.R
import com.emirhanuyunmaz.kotlincryptocurrency.ViewModel.DetailActivityViewModel
import com.emirhanuyunmaz.kotlincryptocurrency.databinding.ActivityDetailBinding
import com.emirhanuyunmaz.kotlincryptocurrency.downloadFromUrl
import com.emirhanuyunmaz.kotlincryptocurrency.placesHolderProgressBar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding
    private lateinit var viewModel: DetailActivityViewModel
    private lateinit var cryptoDetailModel: CryptoDetailModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getIntent=intent

        val getId=getIntent.getStringExtra("id")

        viewModel=ViewModelProvider(this).get(DetailActivityViewModel::class.java)
        if (getId != null) {
            viewModel.getDataFromApi(getId)
        }
        observeLiveData()

        binding.ivYoutube.setOnClickListener {

            val intent =Intent(Intent.ACTION_VIEW, Uri.parse(cryptoDetailModel.links!!.youtube[0]))
            startActivity(intent)

        }

        binding.ivWebSite.setOnClickListener {

            val intent =Intent(Intent.ACTION_VIEW, Uri.parse(cryptoDetailModel.links!!.website[0]))
            startActivity(intent)

        }

        binding.ivFacebook.setOnClickListener {

            val intent =Intent(Intent.ACTION_VIEW, Uri.parse(cryptoDetailModel.links!!.facebook[0]))
            startActivity(intent)

        }

    }


    private fun observeLiveData(){

        viewModel.cryptoDetail.observe(this, Observer {detail->

            detail?.let {
                cryptoDetailModel=it
                binding.ivLogo.downloadFromUrl(it.logo!!, placesHolderProgressBar(applicationContext))
                binding.tvDetailName.text=it.name
                binding.tvDetailSymbol.text=it.symbol
            }

        })

    }

}