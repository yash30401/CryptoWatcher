package com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.R
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.databinding.CurrencyItemLayoutBinding
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.models.CryptoCurrency

class TopMarketAdapter(var context:Context,val list:List<CryptoCurrency>):RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder>() {

    inner class TopMarketViewHolder(view:View):RecyclerView.ViewHolder(view){
        var binding=CurrencyItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarketViewHolder {
        return TopMarketViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: TopMarketViewHolder, position: Int) {
        val item=list[position]

        holder.binding.currencyNameText.text=item.name

        Glide.with(context).load(
            "https://s2.coinmarketcap.com/static/img/coins/128x128/"+item.id+".png"
        ).thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyImageView)

        if(item.quotes!![0].percentChange24h>0){
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.currencyChangeTextView.text="+ ${item.quotes[0].percentChange24h} %"
        }else{
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.currencyChangeTextView.text= " ${item.quotes[0].percentChange24h} %"
        }

        holder.binding.currencyPriceTextView.text=item.quotes[0].price.toString()
        holder.binding.currencySymbolTextView.text=item.symbol
    }

    override fun getItemCount(): Int {
       return list.size
    }

}