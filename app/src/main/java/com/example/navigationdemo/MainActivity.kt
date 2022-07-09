package com.example.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main"){
                        composable("main") { MainScreen(navController = navController)}
                        composable("screen1") { Screen01() }
                        composable("screen2/{param}") { backStackEntry -> Screen02(backStackEntry.arguments?.getString("param").toString()) }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val sendParam = "<<I am a string parameter sent by the Main Screen>>"
    Column {
        Text(text = "This is the main screen!!!")
        Button(onClick = { navController.navigate("screen1") }) {
            Text(text = "Navigate to Screen 01")
        }
        Button(onClick = { navController.navigate("screen2/$sendParam") }) {
            Text(text = "Navigate to Screen 02 sending a parameter")
        }
    }
}

@Composable
fun Screen01() {
    Text(text = "Screen 1!!!")
}

@Composable
fun Screen02(param: String){
    Column {
        Text(text = "Screen 2 with parameter from main screen: ")
        Text(text = param, color = Color.Red)
    }
}

