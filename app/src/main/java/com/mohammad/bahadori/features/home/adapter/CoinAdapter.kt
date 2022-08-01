package com.mohammad.bahadori.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mohammad.bahadori.databinding.ItemListCoinBinding
import com.mohammad.bahadori.domain.models.CoinDomainModel

class CoinAdapter(private val onItemClickListener: OnCoinClickListener) : PagingDataAdapter<CoinDomainModel, CoinViewHolder>(POST_COMPARATOR) {

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: CoinViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            ItemListCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding,onItemClickListener)
    }

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<CoinDomainModel>() {
            override fun areContentsTheSame(
                oldItem: CoinDomainModel,
                newItem: CoinDomainModel
            ): Boolean =
                oldItem.price == newItem.price

            override fun areItemsTheSame(
                oldItem: CoinDomainModel,
                newItem: CoinDomainModel
            ): Boolean =
                oldItem.id == newItem.id

        }
    }
}
interface OnCoinClickListener{
    fun onClicked(coinId: Int)
}
