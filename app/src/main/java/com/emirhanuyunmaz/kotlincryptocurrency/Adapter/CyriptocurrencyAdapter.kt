package com.emirhanuyunmaz.kotlincryptocurrency.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CyriptoModel
import com.emirhanuyunmaz.kotlincryptocurrency.View.DetailActivity
import com.emirhanuyunmaz.kotlincryptocurrency.databinding.RecyclerViewRowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CyriptocurrencyAdapter(var cryptoList:ArrayList<CyriptoModel>):RecyclerView.Adapter<CyriptocurrencyAdapter.CyriptoListVH>() {
    private var oldList=ArrayList<CyriptoModel>()


    inner class CyriptoListVH(var binding:RecyclerViewRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CyriptoListVH {
        val binding=RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CyriptoListVH(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CyriptoListVH, position: Int) {
        holder.binding.tvCyriptoName.text=cryptoList[position].name
        holder.binding.tvCyriptoSymbol.text=cryptoList[position].symbol

        holder.itemView.setOnClickListener{

            val intent= Intent(holder.itemView.context,DetailActivity::class.java)

            intent.putExtra("id",cryptoList[position].id)

            holder.itemView.context.startActivity(intent)
        }

    }

    fun searchCrypto(s:String){
        oldList.addAll(cryptoList)
        cryptoList.clear()
        CoroutineScope(Dispatchers.IO).launch {
            for (o in oldList){
                if (o.name!!.lowercase().contains(s) || o.symbol!!.lowercase().contains(s)){
                    cryptoList.add(o)
                }
            }
            withContext(Dispatchers.Main){
                notifyDataSetChanged()
            }
        }

    }

    fun dataRefresh(newCryptoList: ArrayList<CyriptoModel>){
        cryptoList.clear()
        cryptoList.addAll(newCryptoList)
        notifyDataSetChanged()
    }
}