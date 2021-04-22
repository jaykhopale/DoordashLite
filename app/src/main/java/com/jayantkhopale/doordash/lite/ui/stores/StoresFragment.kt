package com.jayantkhopale.doordash.lite.ui.stores

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jayantkhopale.doordash.lite.R
import com.jayantkhopale.doordash.lite.api.StoresResult
import com.jayantkhopale.doordash.lite.data.stores.Store
import com.jayantkhopale.doordash.lite.databinding.StoresFragmentBinding
import com.jayantkhopale.doordash.lite.ui.StoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresFragment : Fragment(R.layout.stores_fragment) {

    private val storesViewModel: StoresViewModel by hiltNavGraphViewModels(R.id.stores_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = StoresFragmentBinding.bind(view)

        val storesAdapter = StoresAdapter({ store: Store ->
            storeClicked(store.id)
        }, { store: Store ->
            storeLiked(store)
        })

        storesAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        val storesLayoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.storesList.apply {
            adapter = storesAdapter
            layoutManager = storesLayoutManager
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), storesLayoutManager.orientation))
        }

        storesViewModel.storesData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is StoresResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is StoresResult.Failure -> {
                    Log.e("StoresFragment", "Error encountered in stores fragment: ${result.exception}")
                    binding.progressBar.visibility = View.GONE
                    binding.errorMessage.visibility = View.VISIBLE
                }
                is StoresResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val likedStores = storesViewModel.getLikedStores(requireContext())
                    storesAdapter.likedStores = likedStores
                    storesAdapter.submitList(result.stores)
                    Log.e("StoresFragment", "Results size is: ${result.stores.size}")
                }
            }
        }
    }

    private fun storeClicked(id: Int) {
        val action = StoresFragmentDirections.actionStoresFragmentToStoreDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun storeLiked(store: Store) {
        storesViewModel.saveStore(store, requireContext())
    }
}