package com.example.myapplication.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySetBinding

class SetActivity : AppCompatActivity() {
//        private val categoryFragment = CategoryFragment()
//    private val wordFragment = WordFragment()
    lateinit var binding: ActivitySetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = findNavController(R.id.containerrrr)
        binding.bottomNavvv.setupWithNavController(navController)

//                replaceFragment(categoryFragment)
//        binding.bottomNav.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.category -> replaceFragment(categoryFragment)
//                R.id.word -> replaceFragment(wordFragment)
//            }
//            true
//        }

    }

    fun showBottomNavigation()
    {
        binding.bottomNavvv.visibility = View.VISIBLE



    }

    fun hideBottomNavigation()
    {
        binding.bottomNavvv.visibility = View.GONE
        val fragmenttt = findViewById<View>(R.id.asad)
    }

//        private fun replaceFragment(fragment: Fragment){
//        if (fragment != null){
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.containerrrr,fragment)
//            transaction.commit()
//        }
//    }

}