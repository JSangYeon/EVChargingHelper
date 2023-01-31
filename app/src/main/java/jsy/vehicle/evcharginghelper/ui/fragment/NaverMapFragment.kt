package jsy.vehicle.evcharginghelper.ui.fragment

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import dagger.hilt.android.AndroidEntryPoint
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.base.BaseFragment
import jsy.vehicle.evcharginghelper.databinding.FragmentNaverMapBinding
import jsy.vehicle.evcharginghelper.viewmodels.NaverMapViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class NaverMapFragment : BaseFragment<FragmentNaverMapBinding>(R.layout.fragment_naver_map) {

    private val _naverMapViewModel: NaverMapViewModel by activityViewModels()

    private lateinit var naverMap: MapFragment

    override fun FragmentNaverMapBinding.init() {
        Log.d(logTag, "init naverMapFragment")
        binding.naverMapFragment = this@NaverMapFragment
        binding.naverMapViewModel = _naverMapViewModel

        _naverMapViewModel.getEVCS()

        naverMap = (binding.fcNaverMap.getFragment() as MapFragment)

        naverMap.getMapAsync { naverMap -> //  naverMapInit
            Log.d(logTag, "naverMapInit : $naverMap")
            initNaverMapSetting(naverMap)
        }

    }


    private fun initNaverMapSetting(naverMap: NaverMap) {


        _naverMapViewModel.currentLocation.observe(viewLifecycleOwner) { latLng ->

            val cameraUpdate = CameraUpdate.scrollTo(latLng)
                .reason(3)
                .animate(CameraAnimation.Easing, 2000)
                .finishCallback {
                    Toast.makeText(context, "완료", Toast.LENGTH_SHORT).show()
                }
                .cancelCallback {
                    Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
                }
            naverMap.moveCamera(cameraUpdate)
        }

        _naverMapViewModel.markerList.observe(viewLifecycleOwner) { markerList ->

            markerList.forEach { marker ->
//                Log.d(logTag , "marker set : ${marker.tag}\nposition : ${marker.position}")

                marker.setOnClickListener {
                    _naverMapViewModel.getRoute(marker.captionText, marker.position)
                    true
                }
                marker.map = naverMap

            }

        }

        Log.d(logTag, "naverMapViewModel Routepath value : ${_naverMapViewModel.routePath.value}")
        _naverMapViewModel.routePath.observe(viewLifecycleOwner) { path ->
            Log.d(
                logTag,
                "naverMapViewModel observe Routepath value : ${_naverMapViewModel.routePath.value}"
            )

            path.map = naverMap

//
//            routePath.forEach { marker->
//                Log.d(logTag , "marker set : ${marker.tag}\nposition : ${marker.position}")
//
//                marker.setOnClickListener {
//                    _naverMapViewModel.getRoute(marker.position)
//                    true
//                }
//                marker.map = naverMap
//
//            }

        }

    }


    private fun initCamera(naverMap: NaverMap) {

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.5261, 126.8643))
            .reason(3)
            .animate(CameraAnimation.Easing, 2000)
            .finishCallback {
                Toast.makeText(context, "완료", Toast.LENGTH_SHORT).show()
            }
            .cancelCallback {
                Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
            }

//        val marker = Marker().apply {
//            position = LatLng(37.5261, 126.8643)
//            setOnClickListener {
//                Toast.makeText(context, "마커 클릭됨", Toast.LENGTH_SHORT).show()
//                true
//            }
//            map = naverMap
//        }
        naverMap.moveCamera(cameraUpdate)

    }


}