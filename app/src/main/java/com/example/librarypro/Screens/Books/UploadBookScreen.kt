package com.example.librarypro.Screens.Books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.librarypro.Navigation.ROUTE_UPDATE_BOOK
import com.example.librarypro.data.BookViewModel
import com.example.librarypro.model.Upload

@Composable
fun ViewUploadsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var bookRepository = BookViewModel(navController, context)


        val emptyUploadState = remember { mutableStateOf(Upload("","","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Upload>() }

        var uploads = bookRepository.viewUpload(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All uploads",
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(uploads){
                    UploadItem(
                        title = it.title,
                        auther = it.auther,
                        isbn = it.isbn,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        navController = navController,
                        bookRepository = bookRepository
                    )
                }
            }
        }
    }
}


@Composable
fun UploadItem(title:String, auther:String, isbn:String, imageUrl:String, id:String,
               navController:NavHostController, bookRepository:BookViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title)
        Text(text = auther)
        Text(text = isbn)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Row {

            Button(onClick = {
                navController.navigate(ROUTE_UPDATE_BOOK+"/$id")
            }, colors = ButtonDefaults.buttonColors(Color.Green),) {
                Text(text = "Update Book",fontFamily = FontFamily.Serif, )
            }
            Button(onClick = {
                bookRepository.deleteBook(id)
            },colors = ButtonDefaults.buttonColors(Color.Red), ) {
                Text(text = "Delete Book",fontFamily = FontFamily.Serif)
            }
        }
    }
}

