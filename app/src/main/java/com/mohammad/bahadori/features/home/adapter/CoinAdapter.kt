package com.mohammad.bahadori.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.databinding.ItemListCoinBinding
import com.mohammad.bahadori.domain.models.CoinDomainModel

class CoinAdapter()
    : PagingDataAdapter<CoinDomainModel, CoinViewHolder>(POST_COMPARATOR) {

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
            //holder.updateScore(item)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            ItemListCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<CoinDomainModel>() {
            override fun areContentsTheSame(oldItem: CoinDomainModel, newItem: CoinDomainModel): Boolean =
                oldItem.price == newItem.price

            override fun areItemsTheSame(oldItem: CoinDomainModel, newItem: CoinDomainModel): Boolean =
                oldItem.id == newItem.id

//            override fun getChangePayload(oldItem: CoinEntity, newItem: CoinEntity): Any? {
//                return if (sameExceptScore(oldItem, newItem)) {
//                    PAYLOAD_SCORE
//                } else {
//                    null
//                }
//            }
        }

//        private fun sameExceptScore(oldItem: CoinEntity, newItem: CoinEntity): Boolean {
//            // DON'T do this copy in a real app, it is just convenient here for the demo :)
//            // because reddit randomizes scores, we want to pass it as a payload to minimize
//            // UI updates between refreshes
//            return oldItem.copy(score = newItem.score) == newItem
//        }
    }
}
