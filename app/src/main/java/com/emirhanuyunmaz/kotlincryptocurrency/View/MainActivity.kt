package com.emirhanuyunmaz.kotlincryptocurrency.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirhanuyunmaz.kotlincryptocurrency.Adapter.CyriptocurrencyAdapter
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CyriptoModel
import com.emirhanuyunmaz.kotlincryptocurrency.ViewModel.MainActivityViewModel
import com.emirhanuyunmaz.kotlincryptocurrency.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var cyrptoList:ArrayList<CyriptoModel>
    private lateinit var adapter: CyriptocurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getData()
        cyrptoList= ArrayList()

        observeLiveData()

        adapter=CyriptocurrencyAdapter(cyrptoList)
        binding.rvCyriptoList.layoutManager=LinearLayoutManager(this)
        binding.rvCyriptoList.adapter=adapter


        binding.refreshLayout.setOnRefreshListener {
            viewModel.getDataFromAPI()
            binding.refreshLayout.isRefreshing=false
        }

        binding.searchView2.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!= null){
                    adapter.searchCrypto(newText)

                }
                return true
            }


        })


    }


    private fun observeLiveData(){

        viewModel.cyriptoList.observe(this, Observer {crypto->

            crypto?.let {

                binding.progressBar.visibility=View.GONE
                binding.tvError.visibility=View.GONE
                binding.rvCyriptoList.visibility=View.VISIBLE
                adapter.dataRefresh(ArrayList(it))
                //viewModel.getImages(it)
                /*adapter=CyriptocurrencyAdapter(cyrptoList)
                binding.rvCyriptoList.layoutManager=LinearLayoutManager(this)
                binding.rvCyriptoList.adapter=adapter
                */
            }
        })

        viewModel.loading.observe(this, Observer {loading->

            loading?.let {
                if (it){
                    binding.progressBar.visibility= View.VISIBLE
                    binding.rvCyriptoList.visibility=View.INVISIBLE
                    binding.tvError.visibility=View.GONE
                }
            }

        })

        viewModel.errorList.observe(this, Observer {error->

            error?.let {
               if (it){
                   binding.tvError.visibility=View.VISIBLE
                   binding.progressBar.visibility=View.INVISIBLE
                   binding.rvCyriptoList.visibility=View.INVISIBLE
               }
            }

        })

    }
}