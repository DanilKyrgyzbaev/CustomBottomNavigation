package com.example.custombottomnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.custombottomnavigation.ui.theme.CustomBottomNavigationTheme
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.custombottomnavigation.ItemsMenu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomBottomNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Pentala()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Pentala() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigationItem = listOf(
        Home,
        Pets,
        Stars,
        Books,
        Profile
    )


    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {NavigationIndecator(navController, navigationItem)},
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {Fab(scope, scaffoldState)},
        isFloatingActionButtonDocked = true
    ){
        NavigationHost(navController = navController)
    }

}
@Composable
fun currentRoute(navController: NavHostController): String?{
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}
@Composable
fun NavigationIndecator(
    navController: NavHostController,
    menuItems: List<ItemsMenu>
){
    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
            .height(65.dp)
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)).shadow(22.dp),
        backgroundColor = Color.White,
        elevation = 22.dp
    ) {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth().height(65.dp).clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)).shadow(22.dp),
            backgroundColor = Color.White,
            elevation = 22.dp
        ) {
            val currentRoute = currentRoute(navController = navController)
            menuItems.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.ruta,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        item.icon?.let { iconResourceId ->
                            Icon(
                                painter = painterResource(id = iconResourceId),
                                contentDescription = item.title
                            )
                        }
                    },
                    label = { Text(text = item.title) }
                )
            }
        }
    }
}

@Composable
fun Fab(scope: CoroutineScope, scaffoldState: ScaffoldState){
    FloatingActionButton(
        onClick = {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    "Давай что то сделаем ",
                    actionLabel = "a это просто Action Label",
                duration = SnackbarDuration.Indefinite) }
        }
    ) {
        Icon(painter = painterResource(R.drawable.baseline_add_24), contentDescription = null )
    }
}
@Preview(showBackground = true)
@Composable
fun PentalaPreview() {
    CustomBottomNavigationTheme {
        Pentala()
    }
}