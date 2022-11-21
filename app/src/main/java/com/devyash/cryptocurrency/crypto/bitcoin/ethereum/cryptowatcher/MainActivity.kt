package com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)







    }
}