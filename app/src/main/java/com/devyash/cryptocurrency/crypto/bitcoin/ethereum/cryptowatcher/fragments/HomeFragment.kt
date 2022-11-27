package com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.fragments

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.NetworkConnection
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.R
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.adapter.TopMarketAdapter
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.apis.ApiInterface
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.apis.ApiUtilities
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentHomeBinding.bind(view)


        val networkConnection=NetworkConnection(requireContext())

        networkConnection.observe(viewLifecycleOwner, Observer {isConnected->
                if (isConnected){

                    getTopCurrencyList()
                }else{
                    Toast.makeText(context, "You Are Offline", Toast.LENGTH_LONG).show()
                    binding.recylerview.removeAllViewsInLayout()
                    binding.loadinganim.visibility=View.VISIBLE
                }

        })










        binding.SwipeRefreshLayout.setOnRefreshListener {
            getTopCurrencyList()
            binding.SwipeRefreshLayout.isRefreshing=false
        }


    }

    private fun getTopCurrencyList(){


//        if(binding.recylerview.si)
            binding.loadinganim.visibility=View.VISIBLE


        lifecycleScope.launch (Dispatchers.IO){
            val res=ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()



            withContext(Dispatchers.Main){
                binding.loadinganim.visibility=View.GONE
                binding.recylerview.adapter=TopMarketAdapter(requireContext(),res.body()!!.data.cryptoCurrencyList)



            }




        }
    }









}