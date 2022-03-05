package com.example.myapplication.bottom_fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.SplashActivity
import com.example.myapplication.adapters.MainView2Adapter
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.entity.Category
import com.example.myapplication.settings.SetActivity
import com.example.myapplication.settings.SettingsFragment
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    val animalsArray = arrayOf(
        "Cat",
        "Dog",
        "Bird"
    )

    lateinit var binding: FragmentHomeBinding
    lateinit var appDatabase: AppDatabase
    lateinit var categorylist:ArrayList<Category>
    private var adapter: MainView2Adapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)

        checkDatabase()
        setToolbar()
        setViewpager()



        return binding.root
    }

    private fun checkDatabase() {
        categorylist = ArrayList()
        categorylist = appDatabase.categoryDao().getAllKategoria() as ArrayList<Category>

        if (categorylist.isNullOrEmpty()){
            val category = Category()
            category.cat_name="Technology"
            appDatabase.categoryDao().addCategory(category)

            category.cat_name="Sport"
            appDatabase.categoryDao().addCategory(category)

            category.cat_name="News"
            appDatabase.categoryDao().addCategory(category)

            category.cat_name="Lifestyle"
            appDatabase.categoryDao().addCategory(category)

            category.cat_name="Nature"
            appDatabase.categoryDao().addCategory(category)

            category.cat_name="People"
            appDatabase.categoryDao().addCategory(category)

            category.cat_name="Film"
            appDatabase.categoryDao().addCategory(category)

            category.cat_name="Business"
            appDatabase.categoryDao().addCategory(category)
        }
    }

    private fun setViewpager() {
        categorylist = ArrayList()
        categorylist = appDatabase.categoryDao().getAllKategoria() as ArrayList<Category>

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = MainView2Adapter(categorylist,childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = categorylist[position].cat_name
        }.attach()
    }

    private fun setToolbar() {
        binding.toolbar.inflateMenu(R.menu.edit_menu)
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId==R.id.edit){
                val intet = Intent(binding.root.context,SetActivity::class.java)
                startActivity(intet)
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
//        setViewpager()



        checkDatabase()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}