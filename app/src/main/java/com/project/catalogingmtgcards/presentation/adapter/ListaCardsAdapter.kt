package com.project.catalogingmtgcards.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.catalogingmtgcards.databinding.CardListItemBinding
import com.project.catalogingmtgcards.domain.model.Card

class ListaCardsAdapter(
    private val context: Context,
    cards: List<Card>
): RecyclerView.Adapter<ListaCardsAdapter.ViewHolder>() {

    private val cards = cards.toMutableList()

    class ViewHolder(private val binding: CardListItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindCard(card:Card) {
            binding.apply {
                cardName.text = card.name
                cardColor.text = card.color
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CardListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bindCard(card)
    }
}