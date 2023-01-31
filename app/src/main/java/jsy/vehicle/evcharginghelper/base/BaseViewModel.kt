package jsy.vehicle.evcharginghelper.base

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import jsy.vehicle.evcharginghelper.EVChargingHelperApplication.Companion.getGlobalApplicationContext
import jsy.vehicle.evcharginghelper.R
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {
    protected val logTag = javaClass.simpleName
    protected val disposables: CompositeDisposable = CompositeDisposable()
    private var backPressedDisposable: Disposable
    protected val backPressedSubject = BehaviorSubject.createDefault(0L) // 생성할 때는 0을 넣는다
    protected val _loading = MutableLiveData<Boolean>().apply {
        postValue(false)
    }
    protected var _error = MutableLiveData<String>()
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<String> get() = _error


    protected val _hideKeyBoardEvent = SingleLiveEvent<Any>()
    protected val _closeEvent = SingleLiveEvent<String>()


    init {
        backPressedDisposable = backPressedSubject
            .buffer(2, 1) // back 버튼을 한 번 누르면, 이전에 눌렀던 시간과 방금 누른 시간 2개의 값을 발행한다.
            .map {
                Pair<Long, Long>(it.get(0), it.get(1))// 비교하기 쉽게 Pair로 변환
            }
            .map { pair ->
                pair.second - pair.first < TimeUnit.SECONDS.toMillis(2)// 두 번째 누른 시간이 첫 번째 누른 시간보다 2초 이내인가

            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { willFinish ->
                if (willFinish) {
                    android.os.Process.killProcess(android.os.Process.myPid())
                    _closeEvent.call()
                } else {
                    Toast.makeText(getGlobalApplicationContext(), getGlobalApplicationContext().getText(
                            R.string.hint_app_close
                        ), Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun finishEvent() {
        backPressedSubject.onNext(System.currentTimeMillis())
    }

    fun showProgress() {
        _loading.value = true
    }

    fun hideProgress() {
        _loading.value = false
    }


    var focusChangeListener: View.OnFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            _hideKeyBoardEvent.call()
        }
    }
}

