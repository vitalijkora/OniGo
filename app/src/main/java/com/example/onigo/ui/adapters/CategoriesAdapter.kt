package com.example.onigo.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onigo.R
import com.example.onigo.models.Product
import kotlinx.android.synthetic.main.category_section.view.*

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<Pair<String, List<Product>>>() {
        override fun areItemsTheSame(
            oldItem: Pair<String, List<Product>>,
            newItem: Pair<String, List<Product>>,
        ): Boolean {
            return  oldItem.first == newItem.first
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, List<Product>>,
            newItem: Pair<String, List<Product>>,
        ): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_section, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val section = differ.currentList[position]
        holder.itemView.apply {
            section_title.text=section.first+ " ("+ section.second.size +")"
            product_tiles_adapter.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ProductTilesAdapter(section.second)
            }
        }
    }

    override fun getItemCount(): Int { return differ.currentList.size }
}