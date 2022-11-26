package com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.R
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.databinding.CurrencyItemLayoutBinding
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.models.CryptoCurrency
import java.math.RoundingMode
import java.text.DecimalFormat

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
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.binding.currencyImageView)

        Glide.with(context).load("https://s3.coinmarketcap.com/generated/sparklines/web/1d/usd/"+item.id+".png")
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.binding.currencyChartImageView
            )





        if(item.quotes!![0].percentChange24h>0){
            val df=DecimalFormat("#,###.##")

            df.roundingMode=RoundingMode.DOWN
            val roundOff=df.format(item.quotes[0].percentChange24h)

            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.currencyChangeImageview.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_arrow_drop_up_24))
            holder.binding.currencyChartImageView.setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY)
            holder.binding.currencyChangeTextView.text="+ ${roundOff} %"
        }else{

            val df=DecimalFormat("#,###.##")

            df.roundingMode=RoundingMode.DOWN
            val roundOff=df.format(item.quotes[0].percentChange24h)

            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.currencyChangeImageview.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_arrow_drop_down_24))
            holder.binding.currencyChartImageView.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
            holder.binding.currencyChangeTextView.text= " ${roundOff} %"
        }

        val df=DecimalFormat("#,###.##")

        df.roundingMode=RoundingMode.DOWN
        val roundOff=df.format(item.quotes[0].price)


        holder.binding.currencyPriceTextView.text="$"+roundOff.toString()
        holder.binding.currencySymbolTextView.text=item.symbol
    }

    override fun getItemCount(): Int {
       return list.size
    }

}