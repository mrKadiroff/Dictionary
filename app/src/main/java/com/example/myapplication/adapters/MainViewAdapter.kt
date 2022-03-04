package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.viewpagerFragments.MainFragment

//class MainViewAdapter (fragmentManager: FragmentManager) :
//    FragmentStatePagerAdapter(
//        fragmentManager,
//        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
//
//    ) {
//
//    override fun getCount(): Int = 4
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        when(position){
//            0 -> (return "Technology")
//            1 -> (return "Sport")
//            2 -> (return "News")
//            3 -> (return "LifeStyle")
//        }
//        return super.getPageTitle(position)
//    }
//
//    override fun getItem(position: Int): Fragment {
//        return MainFragment.newInstance(position + 1)
//    }
//}
