package com.example.random_advice_generator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.random_advice_generator.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initNavigation()
        setListeners()
    }

    /**
     * Initializes the navigationController.
     *
     */
    private fun initNavigation(){
        navController = findNavController(R.id.navHostFragment)

        NavigationUI.setupWithNavController(navView, navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    /**
     * Sets listeners for the navigation buttons.
     *
     */
    private fun setListeners(){
        navView.menu.getItem(0).setOnMenuItemClickListener { onHomeButtonClicked() }
        navView.menu.getItem(1).setOnMenuItemClickListener { onGenerateButtonClicked() }
    }

    /**
     * Handles the homebutton being clicked.
     *
     * @return true if completed.
     */
    private fun onHomeButtonClicked() : Boolean {
        return if (navController.currentDestination?.id != R.id.homeFragment2) {
            NavHostFragment.findNavController(navHostFragment).navigate(R.id.homeFragment2)
            true
        } else {
            false
        }
    }

    /**
     * Handles the generate button being clicked.
     *
     * @return True if completed.
     */
    private fun onGenerateButtonClicked() : Boolean {
        return if (navController.currentDestination?.id != R.id.generateFragment2) {
            NavHostFragment.findNavController(navHostFragment).navigate(R.id.generateFragment2)
            true
        } else {
            false
        }
    }
}
