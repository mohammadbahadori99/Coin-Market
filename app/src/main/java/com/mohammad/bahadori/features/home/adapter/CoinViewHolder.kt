/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mohammad.bahadori.features.home.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mohammad.bahadori.R
import com.mohammad.bahadori.data.local.model.CoinEntity
import com.mohammad.bahadori.databinding.ItemListCoinBinding
import com.mohammad.bahadori.domain.models.CoinDomainModel

/**
 * A RecyclerView ViewHolder that displays a reddit post.
 */ class CoinViewHolder(private val binding: ItemListCoinBinding) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            //onItemCoinClickListener(getItem(bindingAdapterPosition))
        }
    }

    fun bind(item: CoinDomainModel?) {
        binding.coin = item
    }

}