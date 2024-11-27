package com.example.seatscout

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // 커스텀 아이콘 사용하기 위한 설정
        bottomNavigationView = findViewById(R.id.navigation_main)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.itemIconTintList = null

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.stadiumListFragment -> {
                    navController.popBackStack(R.id.stadiumListFragment, false)
                    navController.navigate(R.id.stadiumListFragment)
                    true
                }
                R.id.settingsFragment -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }
                // 다른 메뉴 항목들 추가시
                else -> false
            }
        }

        // 로그인과 회원가입 프래그먼트 처리
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        bottomNavigationView.animate()
            .translationY(bottomNavigationView.height.toFloat())
            .setDuration(300)
            .withEndAction { bottomNavigationView.visibility = View.GONE }
            .start()
    }

    private fun showBottomNav() {
        bottomNavigationView.animate()
            .translationY(0f)
            .setDuration(300)
            .withStartAction { bottomNavigationView.visibility = View.VISIBLE }
            .start()
    }
}