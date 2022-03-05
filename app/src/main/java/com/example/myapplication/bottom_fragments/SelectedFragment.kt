package com.example.myapplication.bottom_fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapters.WordMainAdapter
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.databinding.FragmentSelectedBinding
import com.example.myapplication.entity.Word
import com.example.myapplication.settings.SetActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectedFragment : Fragment() {
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
    lateinit var binding: FragmentSelectedBinding
    lateinit var appDatabase: AppDatabase
    lateinit var wordAdapter: WordMainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectedBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)


        settolbar()



        wordAdapter = WordMainAdapter(object:WordMainAdapter.OnItemClickListener{
            override fun onItemPopClick(word: Word, imageView: ImageView, position: Int) {
                var bundle = Bundle()
                bundle.putInt("position",position)
                bundle.putSerializable("word",word)
                findNavController().navigate(R.id.wordDetailFragment,bundle)
            }

        })

        appDatabase.wordDao().getWordsBySelection("selected")
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

    private fun settolbar() {
        binding.toolbar.inflateMenu(R.menu.edit_menu)
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId==R.id.edit){
                val intet = Intent(binding.root.context, SetActivity::class.java)
                startActivity(intet)
            }
            true
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}