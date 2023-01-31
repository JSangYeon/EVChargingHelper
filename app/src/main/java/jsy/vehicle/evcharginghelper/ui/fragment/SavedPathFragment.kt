package jsy.vehicle.evcharginghelper.ui.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.base.BaseFragment
import jsy.vehicle.evcharginghelper.databinding.FragmentSavedPathBinding
import jsy.vehicle.evcharginghelper.ui.recycler.view.saved.SavedRecyclerViewAdapter
import jsy.vehicle.evcharginghelper.viewmodels.NaverMapViewModel
import jsy.vehicle.evcharginghelper.viewmodels.SavedPathViewmodel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class SavedPathFragment : BaseFragment<FragmentSavedPathBinding>(R.layout.fragment_saved_path) {

    val _savedPathViewmodel: SavedPathViewmodel by viewModels()
    private val _naverMapViewModel: NaverMapViewModel by activityViewModels()

    override fun FragmentSavedPathBinding.init() {
        Log.d(logTag, "init SavedPathFragment")

        binding.savedPathFragment = this@SavedPathFragment
        binding.savedPathViewModel = _savedPathViewmodel

        val adapter = SavedRecyclerViewAdapter { routeHistory ->
            Log.d(logTag, "adapterCallback $routeHistory")
            _savedPathViewmodel.navigateNaverMapFragment(binding.root)
            _naverMapViewModel.setRouteByRouteHistory(routeHistory)
        }



        binding.rvPath.adapter = adapter
        _savedPathViewmodel.pathList.observe(viewLifecycleOwner) { pathList ->
            adapter.replaceAll(pathList.toList())
//            adapter.notifyDataSetChanged()
        }


    }
}