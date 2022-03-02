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


//        binding.name.text = "Hello"

//        //Fragmnets calling
//        val homeFragment = HomeFragment()
//        val slectedFragment = SelectedFragment()
//
//        //default fragmnet
//        makeCurrentFragment(homeFragment)
//
//        //selectListener to the navigation bar
//        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
//            override fun onTabSelected(
//                lastIndex: Int,
//                lastTab: AnimatedBottomBar.Tab?,
//                newIndex: Int,
//                newTab: AnimatedBottomBar.Tab
//            ) {
//
//                //redirecting fragments
//
//
//
//
//
//                when(newIndex){
//                    0 -> makeCurrentFragment(homeFragment);
//                    1 -> makeCurrentFragment(slectedFragment)
//                    else -> makeCurrentFragment(slectedFragment);
//                }
//
//                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
//
//
//            }
//            // An optional method that will be fired whenever an already selected tab has been selected again.
//            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
//                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
//            }
//        });

    }





//    private fun makeCurrentFragment(fragment: Fragment) {
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.asad,fragment)
//            commit()
//        }


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