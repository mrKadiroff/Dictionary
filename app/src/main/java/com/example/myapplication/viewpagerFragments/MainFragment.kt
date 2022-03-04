package com.example.myapplication.viewpagerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapters.WordAdapter
import com.example.myapplication.adapters.WordMainAdapter
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.entity.Category
import com.example.myapplication.entity.Word
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var categoryID: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryID = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding: FragmentMainBinding
    lateinit var appDatabase: AppDatabase
    lateinit var wordAdapter: WordMainAdapter
    lateinit var categorylist:ArrayList<Category>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        categorylist = ArrayList()
        categorylist = appDatabase.categoryDao().getAllKategoria() as ArrayList<Category>
//        binding.text.text = categorylist[categoryID!!-1].cat_name



        wordAdapter = WordMainAdapter(object:WordMainAdapter.OnItemClickListener{
            override fun onItemPopClick(word: Word, imageView: ImageView) {
                findNavController().navigate(R.id.wordDetailFragment)
            }

        })

        appDatabase.wordDao().getWordsByCategoryName(categorylist[categoryID!!-1].cat_name!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Consumer<List<Word>> {
                override fun accept(t: List<Word>?) {
                    wordAdapter.submitList(t)
                }

            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {

                }

            })
        binding.rvWord.adapter = wordAdapter

        return binding.root
    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(categoryID: Int, kursname: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, categoryID)


                }
            }
    }
}


