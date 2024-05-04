package com.example.onigo.ui.adapters

import android.view.LayoutInflater
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

class ProductTilesAdapter(
    private val products: List<Product>
) : RecyclerView.Adapter<ProductTilesAdapter.ProductTilesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTilesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_tile, parent, false)
        return ProductTilesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductTilesViewHolder, position: Int) {
        val productTile = products[position]
        holder.itemView.apply {
            Glide.with(this).load(productTile.image).into(product_tile_image)
            product_tile_image.clipToOutline = true
            product_tile_title.text = productTile.title
            product_tile_price.text = String.format(Locale.JAPAN, "%,d", productTile.price)
            product_tile_price_with_tax.text=String.format(Locale.JAPAN, "%,d", Math.round(productTile.priceWithTax))
            product_tile_unit.text=productTile.unit
            setOnClickListener {
               val bundle = bundleOf("product_id" to productTile.productId)
                it.findNavController().navigate(
                  R.id.action_categoriesFragment_to_ProductFragment,
                    bundle
                )
            }
        }
    }

    override fun getItemCount(): Int = products.size
    class ProductTilesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


