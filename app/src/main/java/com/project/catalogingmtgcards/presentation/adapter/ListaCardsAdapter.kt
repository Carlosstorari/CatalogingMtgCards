package com.project.catalogingmtgcards.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.catalogingmtgcards.databinding.CardListItemBinding
import com.project.catalogingmtgcards.domain.model.Card


class ListaCardsAdapter(
    private val context: Context,
    cards: List<Card>
): RecyclerView.Adapter<ListaCardsAdapter.ViewHolder>() {

    private val cards = cards.toMutableList()

    class ViewHolder(private val binding: CardListItemBinding, private val context: Context): RecyclerView.ViewHolder(binding.root){

        fun bindCard(card: Card) {
            binding.apply {
                cardIv.setUri(card.imgCard)
                cardName.text = card.name
                cardType.text = card.typeLine
                //manaCost.text = card.manaCost
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CardListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, context)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bindCard(card)
    }
}