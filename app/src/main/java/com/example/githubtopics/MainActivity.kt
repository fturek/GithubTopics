package com.example.githubtopics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.githubtopics.ui.theme.GithubTopicsTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.navgraphs.MainNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // missing switching between light and dark theme. Dont have time for that

        setContent {
            GithubTopicsTheme {
                DestinationsNavHost(
                    navGraph = MainNavGraph
                )
            }
        }
    }
}