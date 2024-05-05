package com.example.onigo.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onigo.R
import com.example.onigo.models.Product
import kotlinx.android.synthetic.main.product_tile.view.*
import java.util.Locale

private fun View.onTouchEvent(itemView: View, function: () -> Unit) {

}

class ProductTilesAdapter(
    private val productTiles: List<Product>
) : RecyclerView.Adapter<ProductTilesAdapter.ProductTilesViewHolder>() {
    inner class ProductTilesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val productTile = productTiles[adapterPosition]
                if (position != RecyclerView.NO_POSITION) {
                    val bundle = bundleOf("product_id" to productTile.productId)
                    it.findNavController().navigate(
                        R.id.action_categoriesFragment_to_ProductFragment,
                        bundle
                    )
                }
            }
            itemView.setOnTouchListener {item, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        itemView.container.alpha= 0.6F
                    }
                    MotionEvent.ACTION_CANCEL ->{
                        itemView.container.alpha= 1F
                    }
                    MotionEvent.ACTION_UP -> {
                        item.performClick()
                    }
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTilesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_tile, parent, false)
        return ProductTilesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductTilesViewHolder, position: Int) {
        val productTile = productTiles[position]
        holder.itemView.apply {
            Glide.with(this).load(productTile.image).into(product_tile_image)
            product_tile_image.clipToOutline = true
            product_tile_title.text = productTile.title
            product_tile_price.text = String.format(Locale.JAPAN, "%,d", productTile.price)
            product_tile_price_with_tax.text=String.format(Locale.JAPAN, "%,d", Math.round(productTile.priceWithTax))
            product_tile_unit.text=productTile.unit
        }
    }

    override fun getItemCount(): Int = productTiles.size

}


