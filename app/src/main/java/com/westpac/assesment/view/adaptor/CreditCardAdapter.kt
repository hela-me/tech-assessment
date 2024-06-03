package com.westpac.assesment.view.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.westpac.assesment.databinding.ItemCreditCardBinding
import com.westpac.assesment.model.CreditCard
import javax.inject.Inject

class CreditCardAdapter @Inject constructor() : RecyclerView.Adapter<CreditCardAdapter.CreditCardViewHolder>() {

    inner class CreditCardViewHolder(private val binding: ItemCreditCardBinding) : ViewHolder(binding.root) {
        fun bindView(item: CreditCard) {
            binding.apply {
                cardType.text = item.creditCardType
                cardNumber.text = item.creditCardNumber
                cardExpiryDate.text = item.creditCardExpiryDate
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<CreditCard>() {
        override fun areItemsTheSame(oldItem: CreditCard, newItem: CreditCard): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CreditCard, newItem: CreditCard): Boolean = oldItem == newItem

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var creditCards: List<CreditCard>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardViewHolder {
        return CreditCardViewHolder(
            ItemCreditCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = creditCards.size

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) {
        holder.bindView(creditCards[position])
    }
}