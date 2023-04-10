package jsy.vehicle.evcharginghelper.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.base.BaseActivity
import jsy.vehicle.evcharginghelper.databinding.ActivityMainBinding
import jsy.vehicle.evcharginghelper.util.common.dpToPixel
import jsy.vehicle.evcharginghelper.util.common.pixelToDp
import jsy.vehicle.evcharginghelper.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val _mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        this.onBackPressedDispatcher.addCallback(this, callback)

    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로가기 클릭 시 실행시킬 코드 입력
            Log.e(logTag, "뒤로가기 클릭")
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