package tk.quietdev.quietdictionary.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import tk.quietdev.quietdictionary.R
import tk.quietdev.quietdictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarSetup()
    }



    private fun toolbarSetup() {
        val parentNavHost =
            supportFragmentManager.findFragmentById(R.id.NavHost) as NavHostFragment
        navController = parentNavHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)


        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}