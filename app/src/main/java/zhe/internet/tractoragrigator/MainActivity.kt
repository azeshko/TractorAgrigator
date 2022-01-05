package zhe.internet.tractoragrigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import zhe.internet.tractoragrigator.fragments.AboutFragment
import zhe.internet.tractoragrigator.fragments.AgrigationFragment
import zhe.internet.tractoragrigator.fragments.CatalogPresentationFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
          bottomNavigationView.setupWithNavController(navController)
        val appBarConfig = AppBarConfiguration(setOf(R.id.catalogFragment, R.id.agrigationFragment, R.id.aboutFragment))
        setupActionBarWithNavController(navController, appBarConfig)

    }

}