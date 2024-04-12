package com.example.notes_combined

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notes_combined.ui.theme.NoteDatabase
import com.example.notes_combined.ui.theme.NoteViewModel
import com.example.notes_combined.ui.theme.NotesScreen
import com.example.notes_combined.ui.theme.NotescombinedTheme
import com.example.notes_combined.ui.theme.SplashScreen

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {
    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }
    private val viewModel by viewModels<NoteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(db.dao) as T
                }
            }
        }
    )

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(modifier= Modifier.fillMaxSize()){
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "SplashScreen"  ){
                    composable("SplashScreen"){
                        SplashScreen(navController = navController)
                    }
                    composable("NotesScreen"){
                     NotesScreen(navController = navController,

                         state = state
                         , onEvent = viewModel::onEvent )
                    }
                }
            }

        }
    }
}
