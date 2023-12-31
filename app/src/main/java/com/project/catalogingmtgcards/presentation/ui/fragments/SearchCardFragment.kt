package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.FragmentSearchCardBinding
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.presentation.adapter.ListaCardsAdapter
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModelState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCardFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchCardBinding
    private val viewModel: ScryFallViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCardBinding.inflate(inflater, container, false)
        setupInputSearch()
        return binding.root
    }

    private fun setupInputSearch() {
        val searchView = binding.inputSearchCard
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getCardListItemAutocomplete(newText)
                return false
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCardListItem()
        binding.btnFilterByColor.setOnClickListener {
            DialogFilterColor(requireActivity()).showDialog { query ->
                viewModel.getCardListItem(query)
                binding.shimmerSearchView.visibility = View.VISIBLE
                binding.cardListHome.visibility = View.GONE
            }
        }
        viewModel.getState.observe(viewLifecycleOwner) {
            when (it) {
                is ScryFallViewModelState.Success -> {
                    setupRecyclerView(it.card)
                    binding.shimmerSearchView.visibility = View.GONE
                    binding.cardListHome.visibility = View.VISIBLE
                }

                is ScryFallViewModelState.Loading -> {
                    binding.shimmerSearchView.visibility = View.VISIBLE
                    binding.cardListHome.visibility = View.GONE
                }

                else -> {
                    Log.d("e", "Errorrrrr")
                }
            }
        }
    }

    private fun setupRecyclerView(list: List<Card>) {
        binding.cardListHome.apply {
            adapter = ListaCardsAdapter(requireActivity(), list)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}