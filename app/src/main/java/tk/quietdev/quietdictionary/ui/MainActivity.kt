package tk.quietdev.quietdictionary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import tk.quietdev.quietdictionary.R
import tk.quietdev.quietdictionary.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), ToolbarConfigure {

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

    override fun changeToolBarTitle(text:String) {
        binding.toolbar.title = text.uppercase(Locale.getDefault())
    }

    private fun toolbarSetup() {
        val parentNavHost =
            supportFragmentManager.findFragmentById(R.id.NavHost) as NavHostFragment
        navController = parentNavHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}