package com.example.myapplication.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapters.CategoryAdapter
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.FragmentCategoryBinding
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.databinding.MyDialogBinding
import com.example.myapplication.entity.Category
import com.example.myapplication.viewpagerFragments.MainFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import android.content.Context
import android.view.ContextThemeWrapper
import com.example.myapplication.databinding.MyDeleteDialogBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
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
    lateinit var binding: FragmentCategoryBinding
    lateinit var appDatabase: AppDatabase
    private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)

        setToolbar()
        setRv()

        return binding.root
    }

    private fun setRv() {

        categoryAdapter = CategoryAdapter(object:CategoryAdapter.OnItemClickListener{
            override fun onItemPopClick(category: Category, imageView: ImageView) {

                val wrapper: Context = ContextThemeWrapper(binding.root.context, R.style.popupMenuStyle)
//                val popup = PopupMenu(wrapper, sourceView)



                val popupMenu = PopupMenu(wrapper, imageView)
                popupMenu.inflate(R.menu.popup_menu)

                popupMenu.setOnMenuItemClickListener { item ->
                    val itemId = item?.itemId

                    when (itemId) {
                        R.id.edit -> {

                            val alertDialog = AlertDialog.Builder(binding.root.context)
                            val dialog = alertDialog.create()
                            val dialogView =
                                MyDialogBinding.inflate(LayoutInflater.from(binding.root.context),null,false)

                            dialogView.sarlavha.setText(category.cat_name)


                          dialogView.add.setOnClickListener {
                              category.cat_name = dialogView.sarlavha.text.toString().trim()
                              appDatabase.categoryDao().updateCategory(category)
                              dialog.dismiss()
                          }


                            dialogView.cancel.setOnClickListener {
                                dialog.dismiss()
                            }


                            dialog.setView(dialogView.root)
                            dialog.show()



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
                                appDatabase.categoryDao().deleteCategory(category)
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

        appDatabase.categoryDao().getAllCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Consumer<List<Category>> {
                override fun accept(t: List<Category>?) {
                    categoryAdapter.submitList(t)
                }

            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {

                }

            })
        binding.rv.adapter = categoryAdapter


    }

    private fun setToolbar() {
        binding.tooolbar.inflateMenu(R.menu.add_menu)
        binding.tooolbar.setOnMenuItemClickListener {
            if (it.itemId==R.id.addd){
                val alertDialog = AlertDialog.Builder(binding.root.context)
                val dialog = alertDialog.create()
                val dialogView =
                    MyDialogBinding.inflate(LayoutInflater.from(binding.root.context),null,false)

                dialogView.add.setOnClickListener {
                    val category = Category()
                    category.cat_name = dialogView.sarlavha.text.toString().trim()
                    Observable.fromCallable {
                        appDatabase.categoryDao().addCategory(category)
                    }.subscribe{
                        Toast.makeText(binding.root.context, "added", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }


               dialogView.cancel.setOnClickListener {
                   dialog.dismiss()
               }


                dialog.setView(dialogView.root)
                dialog.show()
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
         * @return A new instance of fragment CategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}