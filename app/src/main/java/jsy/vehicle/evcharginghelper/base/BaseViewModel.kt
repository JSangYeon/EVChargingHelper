package jsy.vehicle.evcharginghelper.base

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jsy.vehicle.evcharginghelper.EVChargingHelperApplication.Companion.getGlobalApplicationContext
import jsy.vehicle.evcharginghelper.R
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {
    protected val logTag = javaClass.simpleName

    private val backPressedChannel = Channel<Unit>(Channel.CONFLATED)
    protected val _loading = MutableLiveData<Boolean>().apply {
        postValue(false)
    }
    protected var _error = MutableLiveData<String>()
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<String> get() = _error

    protected val _hideKeyBoardEvent = SingleLiveEvent<Any>()
    protected val _closeEvent = SingleLiveEvent<String>()

    private val closeJob = Job()

    fun onBackPressed() {
        CoroutineScope(Dispatchers.Main).launch {
            backPressedChannel.send(Unit)
        }
    }
    fun startCloseCountdown() {
        CoroutineScope(Dispatchers.Main + closeJob).launch {
            val firstBackPressed = withTimeoutOrNull(TimeUnit.SECONDS.toMillis(2)) {
                backPressedChannel.receive()
            }
            if (firstBackPressed != null) {
                val secondBackPressed = withTimeoutOrNull(TimeUnit.SECONDS.toMillis(2)) {
                    backPressedChannel.receive()
                }
                if (secondBackPressed != null) {
                    android.os.Process.killProcess(android.os.Process.myPid())
                    _closeEvent.call()
                } else {
                    Toast.makeText(
                        getGlobalApplicationContext(),
                        getGlobalApplicationContext().getText(R.string.hint_app_close),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun stopCloseCountdown() {
        closeJob.cancel()
    }


    override fun onCleared() {
        super.onCleared()
    }


    fun showProgress() {
        _loading.value = true
    }

    fun hideProgress() {
        _loading.value = false
    }


    var focusChangeListener: View.OnFocusChangeListener =
        View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                _hideKeyBoardEvent.call()
            }
        }
}

