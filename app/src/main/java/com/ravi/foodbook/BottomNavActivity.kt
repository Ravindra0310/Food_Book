package com.ravi.foodbook

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ravi.foodbook.databinding.ActivityBottomNavBinding
import kotlinx.android.synthetic.main.activity_bottom_nav.*
import kotlinx.android.synthetic.main.app_tool_bar.*

class BottomNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavBinding

    companion object {
        var tempFileExt: String? = null
        var tempPicPath: Uri? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        setSupportActionBar(my_toolBar)

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_nav)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_post, R.id.navigation_chat, R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    fun hideBottomNavi(){
        nav_view.visibility = View.GONE
    }

    fun showBottomNavi(){
        nav_view.visibility = View.VISIBLE
    }

}