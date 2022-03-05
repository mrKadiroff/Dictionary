package com.example.myapplication.settings

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapters.WordAdapter
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.FragmentCategoryBinding
import com.example.myapplication.databinding.FragmentWordBinding
import com.example.myapplication.databinding.MyDeleteDialogBinding
import com.example.myapplication.databinding.MyDialogBinding
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
 * Use the [WordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordFragment : Fragment() {
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

    lateinit var binding: FragmentWordBinding
    lateinit var appDatabase: AppDatabase
    lateinit var wordAdapter: WordAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWordBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)


        val stringg = arguments?.getSerializable("kat")

        if (stringg!=null){
            val category = arguments?.getSerializable("cat") as Category
            setToolbar()
            setCategoryBasedRV()
            binding.tooflbar.title = category.cat_name
        }else {
            setToolbar()
            setRv()
        }




        return binding.root
    }

    private fun setCategoryBasedRV() {
        val category = arguments?.getSerializable("cat") as Category

        wordAdapter = WordAdapter(object:WordAdapter.OnItemClickListener{
            override fun onItemPopClick(word: Word, imageView: ImageView) {

                val wrapper: Context = ContextThemeWrapper(binding.root.context, R.style.popupMenuStyle)
                val popupMenu = PopupMenu(wrapper, imageView)
                popupMenu.inflate(R.menu.popup_menu)

                popupMenu.setOnMenuItemClickListener { item ->
                    val itemId = item?.itemId

                    when (itemId) {
                        R.id.edit -> {

                            var bundle = Bundle()
                            bundle.putSerializable("value",word)
                            bundle.putString("edit","edit")
                            findNavController().navigate(R.id.addWordFragment,bundle)



                        }
                        R.id.delete -> {


                            val alertDialog = AlertDialog.Builder(binding.root.context)
                            val dialog = alertDialog.create()
                            val dialogView = MyDeleteDialogBinding.inflate(
                                LayoutInflater.from(binding.root.context),
                                null,
                                false
                            )

                            dialogView.add.setOnClickListener {
                                appDatabase.wordDao().deleteWord(word)
                                dialog.dismiss()
                            }

                            dialogView.cancel.setOnClickListener {
                                dialog.dismiss()
                            }

                            dialog.setView(dialogView.root)
                            dialog.show()










                        }
                    }
                    true
                }

                popupMenu.show()

            }

        })

        appDatabase.wordDao().getWordsByCategoryId(category.id!!)
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

    }

    private fun setRv() {
        wordAdapter = WordAdapter(object:WordAdapter.OnItemClickListener{
            override fun onItemPopClick(word: Word, imageView: ImageView) {

                val wrapper: Context = ContextThemeWrapper(binding.root.context, R.style.popupMenuStyle)
                val popupMenu = PopupMenu(wrapper, imageView)
                popupMenu.inflate(R.menu.popup_menu)

                popupMenu.setOnMenuItemClickListener { item ->
                    val itemId = item?.itemId

                    when (itemId) {
                        R.id.edit -> {

                            var bundle = Bundle()
                            bundle.putSerializable("value",word)
                            bundle.putString("edit","edit")
                         findNavController().navigate(R.id.addWordFragment,bundle)



                        }
                        R.id.delete -> {


                            val alertDialog = AlertDialog.Builder(binding.root.context)
                            val dialog = alertDialog.create()
                            val dialogView = MyDeleteDialogBinding.inflate(
                                LayoutInflater.from(binding.root.context),
                                null,
                                false
                            )

                            dialogView.add.setOnClickListener {
                                appDatabase.wordDao().deleteWord(word)
                                dialog.dismiss()
                            }

                            dialogView.cancel.setOnClickListener {
                                dialog.dismiss()
                            }

                            dialog.setView(dialogView.root)
                            dialog.show()










                        }
                    }
                    true
                }

                popupMenu.show()

            }

        })

        appDatabase.wordDao().getAllWord()
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


    }

    private fun setToolbar() {
        binding.tooflbar.inflateMenu(R.menu.add_menu)

        binding.tooflbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }


        binding.tooflbar.setOnMenuItemClickListener {
            if (it.itemId==R.id.addd){
                var bundle = Bundle()
                bundle.putString("add","add")
                findNavController().navigate(R.id.addWordFragment,bundle)
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
         * @return A new instance of fragment WordFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}