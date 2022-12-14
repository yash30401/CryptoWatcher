package com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.databinding.ActivityMainBinding
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.fragments.HomeFragment
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.fragments.MarketFragment
import com.devyash.cryptocurrency.crypto.bitcoin.ethereum.cryptowatcher.fragments.WatchlistFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import nl.joery.animatedbottombar.AnimatedBottomBar


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BottomSheetBehavior.from(binding.bottomsheet).apply {
            peekHeight=binding.bottomBar.height+1300
            this.state=BottomSheetBehavior.STATE_EXPANDED

        }





//        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragment_container_view)
//        val navController=navHostFragment!!.findNavController()
//
//        val popupMenu=PopupMenu(this,null)
//        popupMenu.inflate(R.menu.bottom_menu)
//        binding.bottomBar.setupWithNavController(popupMenu.menu,navController)


        val viewPager: ViewPager2=findViewById(R.id.view_pager)

        val fragments:ArrayList<Fragment> = arrayListOf(HomeFragment(),MarketFragment(),WatchlistFragment())

        val adapter=ViewPagerAdapter(fragments,this)
        viewPager.adapter=adapter

        binding.bottomBar.setupWithViewPager2(viewPager)

    }
}