package com.mr.anonym.imarket.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mr.anonym.imarket.presentation.navigation.NavGraph
import com.mr.anonym.imarket.ui.theme.IMarketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMarketTheme {
                NavGraph()
            }
        }
    }
}