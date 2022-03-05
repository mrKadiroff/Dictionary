package com.example.myapplication.bottom_fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.FragmentAddWordBinding
import com.example.myapplication.databinding.FragmentWordDetailBinding
import com.example.myapplication.entity.Word
import com.example.myapplication.settings.SetActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WordDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordDetailFragment : Fragment() {
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

    lateinit var binding: FragmentWordDetailBinding
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentWordDetailBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)

        setUI()
        setClick()

        return binding.root
    }

    private fun setClick() {
        var a = 100
        val position = arguments?.getInt("position")
        val cameradata = arguments?.getSerializable("word") as Word
        binding.liked.setOnClickListener {
            if (a == position) {
                binding.liked.setImageResource(R.drawable.ic_heart2)
                cameradata.color = R.drawable.ic_heart2
                cameradata.selected = "selected"
                appDatabase.wordDao().updateWord(cameradata)
                a++
            } else {
                binding.liked.setImageResource(R.drawable.ic_favourite)
                cameradata.color = R.drawable.ic_favourite
                cameradata.selected = "unselected"
                appDatabase.wordDao().updateWord(cameradata)
                a = position!!
            }
        }
    }

    private fun setUI() {
        val cameradata = arguments?.getSerializable("word") as Word
        val position = arguments?.getInt("position")
        binding.wordImage.setImageURI(Uri.parse(cameradata.word_photo))
        binding.word.text = cameradata.word
        binding.translation.text = cameradata.translate
        binding.toolbar.title = cameradata.word
        binding.liked.setImageResource(cameradata.color!!)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomNavigation()
        super.onDetach()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WordDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WordDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}