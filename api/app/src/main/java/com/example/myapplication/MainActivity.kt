package com.example.myapplication

import android.os.Bundle
import android.provider.Settings.Global
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val retrofit = Retrofit.Builder().baseUrl("https://official-joke-api.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofit2 = Retrofit.Builder().baseUrl("https://api.quotable.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(Service::class.java)
        val service2 = retrofit2.create(Service2::class.java)
        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val data = withContext(Dispatchers.IO) {
                    return@withContext service.getdate()
                }
                binding.id.text = data.id
                binding.type.text = data.type
                binding.setup.text = data.setup
                binding.punchline.text = data.punchline
            }
        }
        binding.button2.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val data = withContext(Dispatchers.IO) {
                    return@withContext service2.getdate()
                }
                binding.id.text = data.id

                binding.author.text = data.author
                binding.content.text = data.content
            }
        }
    }
}