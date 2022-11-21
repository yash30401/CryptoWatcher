package com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BottomSheetBehavior.from(bottomsheet).apply {
            peekHeight=200

            this.state=BottomSheetBehavior.STATE_HALF_EXPANDED


        }





    }
}