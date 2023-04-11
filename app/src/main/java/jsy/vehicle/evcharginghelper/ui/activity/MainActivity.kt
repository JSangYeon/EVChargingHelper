package jsy.vehicle.evcharginghelper.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.base.BaseActivity
import jsy.vehicle.evcharginghelper.databinding.ActivityMainBinding
import jsy.vehicle.evcharginghelper.util.common.dpToPixel
import jsy.vehicle.evcharginghelper.util.common.pixelToDp
import jsy.vehicle.evcharginghelper.viewmodels.MainViewModel


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val _mainViewModel: MainViewModel by viewModels()
    private val tvList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainViewModel = _mainViewModel
        binding.mainActivity = this@MainActivity

        Log.d(logTag, "startActivity");
        Log.d(logTag, "100dp to pixels : ${100.dpToPixel()}")
        Log.d(logTag, "100pixels to dp : ${100.pixelToDp()}")


//        _mainViewModel.mainText.observe(lifecycleOwner) { text ->
//                binding.tvMain.text = text
//        }
//
//        binding.btnTextChange.setOnClickListener{
//            _mainViewModel.changeMainText()
//        }
        setDrawer(binding.drawerLayout, binding.navgationView,  R.id.nav_home )
        initBottomSheet()

        this.onBackPressedDispatcher.addCallback(this, callback)

    }


    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로가기 클릭 시 실행시킬 코드 입력
            Log.e(logTag, "뒤로가기 클릭")
        }
    }

    private fun initBottomSheet() {

        binding.viewBottomSheet.tvHome.setBackgroundResource(R.color.darker_gray) // 현재 홈화면

        // 각 TextView의 onClick 속성에 메서드를 연결
        tvList.add(binding.viewBottomSheet.tvHome)
        tvList.add(binding.viewBottomSheet.btnClose1)
        tvList.add(binding.viewBottomSheet.btnClose2)
        tvList.add(binding.viewBottomSheet.btnClose3)
        tvList.add(binding.viewBottomSheet.btnClose4)
        tvList.add(binding.viewBottomSheet.btnClose5)
        tvList.add(binding.viewBottomSheet.btnClose6)
        tvList.add(binding.viewBottomSheet.tvDrawer)

        tvList.forEach { textView ->
            textView.setOnClickListener { clickedTextview ->
                setItemBackgroundColor(clickedTextview as TextView)
                bottomSheetItemClick(clickedTextview)
            }
        }

    }

    private fun setItemBackgroundColor(textView: TextView) {
        tvList.forEach { item ->
            when (textView) {
                binding.viewBottomSheet.tvDrawer -> item.setBackgroundResource(R.color.black)
                item -> textView.setBackgroundResource(R.color.darker_gray)
                else -> item.setBackgroundResource(R.color.black)
            }
        }
    }

    private fun bottomSheetItemClick(textView: TextView) {

        when (textView) {
            binding.viewBottomSheet.tvHome -> {
                val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
                val currentFragmentId = navController.currentDestination?.id

                if (currentFragmentId!=null && currentFragmentId != R.id.naver_map_fragment)
                {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_saved_path_to_naver_map)
                }
            }
            binding.viewBottomSheet.btnClose1 -> {
                val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
                val currentFragmentId = navController.currentDestination?.id

                if (currentFragmentId!=null && currentFragmentId != R.id.saved_path_fragment)
                {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_naver_map_to_saved_path)
                }
            }
            binding.viewBottomSheet.tvDrawer -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

    }


//    override fun onBackPressed() {
//        val controller = Navigation.findNavController(this, R.id.nav_host_fragment)
//        Log.d(logTag, "popBackStack false ${controller.backQueue.size}")
//        if(controller.backQueue.size>2) // 쌓여있는 BackStack이 있을경우
//        {
//            controller.popBackStack()
//        }else{
//            _mainViewModel.finishEvent()
////            Toast.makeText(this@MainActivity, "popBackStack false", Toast.LENGTH_SHORT).show()
//        }
//
//    }

}
