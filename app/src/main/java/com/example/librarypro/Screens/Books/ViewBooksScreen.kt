package com.example.librarypro.Screens.Books


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Navigation.ROUTE_ADD_BOOK
import com.example.librarypro.Navigation.ROUTE_DASHBOARD
import com.example.librarypro.Navigation.ROUTE_UPDATE_BOOK
import com.example.librarypro.data.BookViewModel
import com.example.librarypro.model.Books


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewBooksScreen(navController: NavHostController){
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {

        val context = LocalContext.current
        val bookRepository = BookViewModel(navController, context)
        val emptyBookState = remember { mutableStateOf(Books("","","","")) }
        val emptyBooksListState = remember { mutableStateListOf<Books>() }
        val books = bookRepository.viewBooks(emptyBookState, emptyBooksListState)

        Column(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            Text(text = "Available Books",
                fontSize = 35.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Red,
                textDecoration= TextDecoration.Underline)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly){
                items(books){
                    BookItem(
                        title = it.title,
                        auther = it.auther,
                        isbn = it.isbn,
                        id = it.id,
                        navController = navController,
                        bookRepository=bookRepository
                    )
                }
            }
        }
    }

        BottomAppBar() {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { navController.navigate(ROUTE_DASHBOARD) }
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


}

@Composable
fun BookItem(title:String,auther:String,isbn:String,id:String,
             navController:NavHostController, bookRepository: BookViewModel){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Text(text = "Book Title: ", fontFamily = FontFamily.Serif, fontSize = 15.sp)
            Text(text = title, color = Color.Blue, fontFamily = FontFamily.Serif, )
        }
        Row {
            Text(text = "Book Auther: ",fontFamily = FontFamily.Serif, fontSize = 15.sp)
            Text(text = auther, color = Color.Blue, fontFamily = FontFamily.Serif, )
        }
        Row {
            Text(text = "Book ISBN: ",fontFamily = FontFamily.Serif, fontSize = 15.sp)
            Text(text = isbn, color = Color.Blue, fontFamily = FontFamily.Serif,)
        }
        Button(onClick = {
            bookRepository.deleteBook(id)
        }, colors = ButtonDefaults.buttonColors(Color.Red)) {
            Text(text = "Delete Book",fontFamily = FontFamily.Serif, )
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_BOOK+"/$id")
        }, colors = ButtonDefaults.buttonColors(Color.Green),) {
            Text(text = "Update Book",fontFamily = FontFamily.Serif, )
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewBooksPreview(){
    ViewBooksScreen(rememberNavController())
}