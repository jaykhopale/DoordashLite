package com.jayantkhopale.doordash.lite.ui.stores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.jayantkhopale.doordash.lite.R
import com.jayantkhopale.doordash.lite.data.stores.Store
import com.jayantkhopale.doordash.lite.databinding.ItemStoreBinding

class StoresAdapter(private val clickListener: (Store) -> Unit) :
    ListAdapter<Store, StoresAdapter.StoreViewHolder>(storeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val itemBinding = ItemStoreBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return StoreViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    inner class StoreViewHolder(private val itemStoreBinding: ItemStoreBinding) : RecyclerView
    .ViewHolder(itemStoreBinding.root) {
        fun bind(store: Store, clickListener: (Store) -> Unit) {
            itemStoreBinding.storeName.text = store.name
            itemStoreBinding.storeCuisine.text = store.shortDescription
            itemStoreBinding.storeStatus.text = store.deliveryTime
            itemStoreBinding.root.setOnClickListener {
                clickListener(store)
            }

            Glide.with(itemStoreBinding.storeLogo)
                .load(store.coverImgUrl)
                .transform(
                    CenterInside(),
                    RoundedCorners(
                        itemStoreBinding.storeLogo.resources.getDimension(R.dimen.corner_radius)
                            .toInt()
                    )
                )
                .into(itemStoreBinding.storeLogo)
        }
    }

    companion object {
        private val storeDiffCallback = object : DiffUtil.ItemCallback<Store>() {

            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem == newItem
            }
        }
    }
}