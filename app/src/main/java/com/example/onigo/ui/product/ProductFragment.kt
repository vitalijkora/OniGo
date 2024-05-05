package com.example.onigo.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onigo.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product.back_button
import kotlinx.android.synthetic.main.product_tile.view.container

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val mBinding get() = _binding!!
    private val bundleArgs: ProductFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductBinding.inflate(layoutInflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productIdArg = bundleArgs.productId

        back_button.setOnClickListener {
            findNavController().popBackStack()
        }
        back_button.setOnTouchListener {item, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    back_button.alpha= 0.6F
                }
                MotionEvent.ACTION_CANCEL ->{
                    back_button.alpha= 1F
                }
                MotionEvent.ACTION_UP -> {
                    item.performClick()
                }
            }
            true
        }
        productIdArg.let { id ->
            mBinding.productId.text = "Product Id: \"$id\""
        }

    }
}

