package com.example.onigo.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onigo.utils.Resource
import com.example.onigo.databinding.FragmentCategoryBinding
import com.example.onigo.ui.adapters.CategoryAdapter
import com.example.onigo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_category.category_adapter
import kotlinx.android.synthetic.main.fragment_category.pag_progress_bar

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val mBinding get() = _binding!!

    private val viewModel by viewModels<CategoryViewModel>()
    lateinit var categoryAdapter: CategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                initAdapter()

        categoryAdapter.setOnItemClickListener {
            val bundle = bundleOf("product_id" to it)
            view.findNavController().navigate(
                R.id.action_categoryFragment_to_ProductFragment,
                bundle
            )
        }

        viewModel.categoriesData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    pag_progress_bar.visibility = View.INVISIBLE
                    response.data?.let {

                        categoryAdapter.differ.submitList(it.COSTCO)
                    }
                }
                is Resource.Error -> {
                    pag_progress_bar.visibility = View.INVISIBLE
                    response.data?.let {
                    }
                }
                is Resource.Loading -> {
                    pag_progress_bar.visibility = View.VISIBLE
                }
            }
        }
    }
    private fun initAdapter() {
        categoryAdapter = CategoryAdapter()
        category_adapter.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}