package com.example.onigo.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_tile.view.*
import com.example.onigo.R
import com.example.onigo.models.COSTCO
import java.util.Locale

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<COSTCO>() {
        override fun areItemsTheSame(oldItem: COSTCO, newItem: COSTCO): Boolean {
            return oldItem.idx == newItem.idx
        }

        override fun areContentsTheSame(oldItem: COSTCO, newItem: COSTCO): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_tile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val product_tile = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(product_tile.image).into(product_tile_image)
            product_tile_image.clipToOutline = true
            product_tile_title.text = product_tile.title
            product_tile_price.text = String.format(Locale.JAPAN, "%,d", product_tile.price)
            product_tile_price_with_tax.text=String.format(Locale.JAPAN, "%,d", Math.round(product_tile.priceWithTax))
            product_tile_unit.text=product_tile.unit.toString()
            setOnClickListener {
               onProductClickListener?.let { it(product_tile.productId) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onProductClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onProductClickListener = listener
    }

}