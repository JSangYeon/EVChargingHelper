package jsy.vehicle.evcharginghelper.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.navigation.NavigationView
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.ui.activity.MainActivity
import jsy.vehicle.evcharginghelper.ui.activity.SecondActivity

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    AppCompatActivity() {
    protected lateinit var binding: T
    protected lateinit var logTag: String
    protected lateinit var lifecycleOwner: LifecycleOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        lifecycleOwner = this
        logTag = this.localClassName
    }

    protected fun setDrawer(
        drawerLayout: DrawerLayout,
        navigationView: NavigationView,
        currentResourceId: Int,
    ) {
//        binding.setVariable(BR.toolbarTitle, getString(toolbarTitleRes))
//        binding.setVariable(BR.drawerLayout, drawerLayout)

//        if(navigationView.getHeaderView(0)!=null)
//        {
//            val headerBinding = NavHeaderMainBinding.bind(navigationView.getHeaderView(0)) // DrawerLayout HeaderView DataBinding
//            headerBinding.setVariable(BR.drawerLayout, drawerLayout) // drawerLayout set
//        }

        navigationView.setNavigationItemSelectedListener {

            if (it.itemId == currentResourceId) {

                Log.d("testLog", "here")
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                val intent = when (it.itemId) {
                    R.id.nav_home -> {
                        Intent(this@BaseActivity, MainActivity::class.java)
                    }
                    R.id.nav_second -> {
                        Intent(this@BaseActivity, SecondActivity::class.java)
                    }
                    R.id.nav_version -> {
                        Intent( this@BaseActivity, SecondActivity::class.java)
                    }
                    else -> {
                        Intent(this@BaseActivity, MainActivity::class.java)
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(intent);
            }
            false
        }
    }

}