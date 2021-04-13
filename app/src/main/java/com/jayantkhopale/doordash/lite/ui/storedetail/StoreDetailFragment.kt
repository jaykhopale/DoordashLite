package com.jayantkhopale.doordash.lite.ui.storedetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.jayantkhopale.doordash.lite.R
import com.jayantkhopale.doordash.lite.api.StoreDetailResult
import com.jayantkhopale.doordash.lite.databinding.StoreDetailFragmentBinding
import com.jayantkhopale.doordash.lite.ui.StoresViewModel

class StoreDetailFragment : Fragment(R.layout.store_detail_fragment) {

    private val storesViewModel: StoresViewModel by hiltNavGraphViewModels(R.id.stores_graph)
    private val args: StoreDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            storesViewModel.getStoreDetail(args.storeId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = StoreDetailFragmentBinding.bind(view)

        storesViewModel.storeDetailResult.observe(viewLifecycleOwner) { detailResult: StoreDetailResult ->
            when (detailResult) {
                is StoreDetailResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is StoreDetailResult.Failure -> {
                    Log.e("StoreDetailFragment", "Error encountered in stores fragment: " +
                            "${detailResult.exception}")
                    binding.progressBar.visibility = View.GONE
                    binding.errorMessage.visibility = View.VISIBLE
                }
                is StoreDetailResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val storeDetail = detailResult.storeDetail
                    Glide.with(this)
                        .load(storeDetail.coverImgUrl)
                        .placeholder(R.drawable.ic_baseline_store_24)
                        .transform(
                            CenterInside(),
                        ).into(binding.storeCover)
                    binding.deliveryFee.text = storeDetail.deliveryFeeDetails.finalFee.displayString
                    binding.deliveryStatus.text = storeDetail.status
                    binding.storeName.text = storeDetail.name
                    binding.storeRating.rating = storeDetail.averageRating.toFloat()
                    binding.storeTags.text = storeDetail.tags.joinToString()
                    binding.ratingCount.text = getString(
                        R.string.ratings_placeholder,
                        storeDetail.averageRating.toString(), storeDetail.numberOfRatings
                    )

                    binding.storeRating.visibility = View.VISIBLE
                    binding.feeDisplay.visibility = View.VISIBLE
                    binding.statusDisplay.visibility = View.VISIBLE
                }
            }
        }
    }
}