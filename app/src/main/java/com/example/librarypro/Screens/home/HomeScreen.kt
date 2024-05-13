package com.example.librarypro.Screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Navigation.ROUTE_ADD_BOOK
import com.example.librarypro.Navigation.ROUTE_BORROWED_BOOKS
import com.example.librarypro.Navigation.ROUTE_BORROW_BOOK
import com.example.librarypro.Navigation.ROUTE_DASHBOARD
import com.example.librarypro.Navigation.ROUTE_RETURN_BOOK
import com.example.librarypro.Navigation.ROUTE_SEARCH_BOOK
import com.example.librarypro.Navigation.ROUTE_SIGNUP
import com.example.librarypro.Navigation.ROUTE_VIEW_BOOK
import com.example.librarypro.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {



//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Library Pro", fontFamily = FontFamily.Serif, color = Color.Blue) },
//                navigationIcon = {
//                    IconButton(onClick = { navController.navigate(ROUTE_DASHBOARD) }) {
//                        Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { navController.navigate(ROUTE_SEARCH_BOOK) }) {
//                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
//                    }
//                    IconButton(onClick = { navController.navigate(ROUTE_SIGNUP) }) {
//                        Icon(
//                            imageVector = Icons.Filled.AccountBox,
//                            contentDescription = "Account"
//                        )
//                    }
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        }
//    ) {
        Surface(color = Color.White) {
            BottomAppBar() {
                BottomNavigation {
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Home") },
                        label = { Text("My Profile") },
                        selected = true,
                        onClick = { navController.navigate(ROUTE_SIGNUP) }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Add, contentDescription = "Library") },
                        label = { Text("Add Book") },
                        selected = false,
                        onClick = { navController.navigate(ROUTE_ADD_BOOK) }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                        label = { Text("Settings") },
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Row {
                    ClickableCard2 {
                        navController.navigate(ROUTE_BORROW_BOOK)
                    }
                    ClickableCard3 {
                        navController.navigate(ROUTE_ADD_BOOK)
                    }
                }
                Row {
                    ClickableCard {
                        navController.navigate(ROUTE_VIEW_BOOK)
                    }
                    ClickableCard4 {
                        navController.navigate(ROUTE_BORROWED_BOOKS)
                    }
                }

            }



        }
    }


@Composable
fun DashboardButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = text)
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(rememberNavController())
}

@Composable
fun ClickableCard2(
//    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
//        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(170.dp)
//                .fillMaxWidth()
        ) {
            Image(
//                painter = rememberImagePainter(imageUrl),
                painter = painterResource(id = R.drawable.image1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { onClick() }
            ) {
                Text(
                    text = "Borrow Books",
                    color = Color.Blue,
                    fontFamily = FontFamily.Serif,
//                    textDecoration = TextDecoration.Underline,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
@Composable
fun ClickableCard3(
//    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
//        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(170.dp)
//                .fillMaxWidth()
        ) {
            Image(
//                painter = rememberImagePainter(imageUrl),
                painter = painterResource(id = R.drawable.image2),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { onClick() }
            ) {
                Text(
                    text = "Add Books",
                    color = Color.Blue,
                    fontFamily = FontFamily.Serif,
//                    textDecoration = TextDecoration.Underline,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
@Composable
fun ClickableCard(
//    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
//        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(170.dp)
//                .fillMaxWidth()
        ) {
            Image(
//                painter = rememberImagePainter(imageUrl),
                painter = painterResource(id = R.drawable.image3),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { onClick() }
            ) {
                Text(
                    text = "View Books",
                    color = Color.Blue,
                    fontFamily = FontFamily.Serif,
//                    textDecoration = TextDecoration.Underline,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
@Composable
fun ClickableCard4(
//    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
//        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(170.dp)
//                .fillMaxWidth()
        ) {
            Image(
//                painter = rememberImagePainter(imageUrl),
                painter = painterResource(id = R.drawable.image5),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { onClick() }
            ) {
                Text(
                    text = "Borrowed Books",
                    color = Color.Blue,
                    fontFamily = FontFamily.Serif,
//                    textDecoration = TextDecoration.Underline,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

