package com.example.librarypro.Screens.Books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Navigation.ROUTE_DASHBOARD
import com.example.librarypro.R


val LocalWebsiteUrl = compositionLocalOf<String> { error("No website URL provided") }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBooksScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.image6),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val onTextLayout: (TextLayoutResult) -> Unit = {}

        Button(
            onClick = {

//                val context = LocalContext.current
//
//                val webpage = Uri.parse("http://www.example.com")
//                val intent = Intent(Intent.ACTION_VIEW, webpage)
//                context.startActivity(intent)


                /* Navigate to library website */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Search Online",onTextLayout = onTextLayout)
        }
        Button(
            onClick = { navController.navigate("add_books") },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Search Offline",
                onTextLayout = onTextLayout)
        }
    }
//    ImageComponent(image = R.drawable.image5)
    TopAppBar(
        title = { Text(text = "Library Pro") },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(ROUTE_DASHBOARD) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
        },
        actions = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBooksScreenPreview(){
    SearchBooksScreen(rememberNavController())
}