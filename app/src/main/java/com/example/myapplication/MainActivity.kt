package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.bottom_fragments.HomeFragment
import com.example.myapplication.bottom_fragments.SelectedFragment
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.asad)
        binding.bottomNav.setupWithNavController(navController)




    }








    fun showBottomNavigation()
    {
        binding.bottomNav.visibility = View.VISIBLE



    }

    fun hideBottomNavigation()
    {
        binding.bottomNav.visibility = View.GONE
        val fragmenttt = findViewById<View>(R.id.asad)
    }
}