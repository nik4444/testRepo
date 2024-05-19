package com.app.testapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.testapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* lifecycleScope.launch {
            viewModel.data.collect{result ->
                when (result) {
                    is Resource.Success -> {
                        val data = result.data.data

                        Log.d("data===>", data.joinToString(" * "))
                    }
                    else ->{

                    }
                }

            }
        }*/
        lifecycleScope.launch {
            viewModel.dataFromDb.collect{result ->
                val data = result
                binding.tvData.text = data.joinToString("\n")
                Log.d("data===>", data.joinToString("\n"))
            }
        }

        viewModel.getDataFromDb()


    }
}