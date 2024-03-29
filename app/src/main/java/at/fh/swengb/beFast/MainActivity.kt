package at.fh.swengb.beFast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import at.fh.swengb.beFast.drops.recyclerview.DropsRepository

import at.fh.swengb.beFast.news.NewsFragment

import at.fh.swengb.beFast.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        /** Bottom Navigation Menu
         * Passing each menu ID as a set of Ids because each
         * menu should be considered as top level destinations.
         */
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_news, R.id.navigation_brands, R.id.navigation_drops, R.id.navigation_more))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /** Nightmode */
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean(SettingsActivity.nightModeKey, true)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}