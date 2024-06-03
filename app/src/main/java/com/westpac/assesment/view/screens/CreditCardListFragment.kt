package com.westpac.assesment.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.westpac.assesment.databinding.FragmentCreditCardListBinding
import com.westpac.assesment.util.collectLatestLifecycleFlow
import com.westpac.assesment.util.init
import com.westpac.assesment.view.adaptor.CreditCardAdapter
import com.westpac.assesment.view.viewmodel.CreditCardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreditCardListFragment : Fragment() {

    private var _binding: FragmentCreditCardListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreditCardViewModel by viewModels()

    @Inject
    lateinit var creditCardAdapter: CreditCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCreditCardListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.creditCardRecyclerView.init(LinearLayoutManager(requireContext()), creditCardAdapter)

        collectLatestLifecycleFlow(viewModel.creditCards) { creditCards ->
            creditCardAdapter.creditCards = creditCards
        }

        collectLatestLifecycleFlow(viewModel.isLoadingData) { isLoading ->
            binding.progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        collectLatestLifecycleFlow(viewModel.apiFailure) { failureMessage ->
            Snackbar.make(binding.root, failureMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}