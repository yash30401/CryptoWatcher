package com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.recyclerview.widget.LinearLayoutManager
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.R
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.adapter.TopMarketAdapter
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.apis.ApiInterface
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.apis.ApiUtilities
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentHomeBinding.bind(view)


        getTopCurrencyList()

        binding.SwipeRefreshLayout.setOnRefreshListener {
            getTopCurrencyList()
            binding.SwipeRefreshLayout.isRefreshing=false
        }


    }

    private fun getTopCurrencyList(){
        lifecycleScope.launch (Dispatchers.IO){
            val res=ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()


            withContext(Dispatchers.Main){
                binding.recylerview.adapter=TopMarketAdapter(requireContext(),res.body()!!.data.cryptoCurrencyList)
            }


        }
    }

}