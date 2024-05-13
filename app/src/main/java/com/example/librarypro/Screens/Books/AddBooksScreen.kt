package com.example.librarypro.Screens.Books


import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Components.ImageComponent
import com.example.librarypro.Navigation.ROUTE_DASHBOARD
import com.example.librarypro.Navigation.ROUTE_VIEW_BOOK
import com.example.librarypro.Navigation.ROUTE_VIEW_UPLOAD
import com.example.librarypro.R
import com.example.librarypro.data.BookViewModel

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    val context = LocalContext.current



    Surface(color = Color.White) {

        TopAppBar(
            title = { Text(text = "Library Pro") },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(ROUTE_DASHBOARD) }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                }
            },
            actions = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Search")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
//            .background(BgSocial)

        ) {

            Spacer(modifier = Modifier.height(50.dp))

//            ImageComponent(image = R.drawable.image8)
            val onTextLayout: (TextLayoutResult) -> Unit = {}
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title", onTextLayout = onTextLayout) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = author,
                onValueChange = { author = it },
                label = {

                    Text(
                        "Author",
                        onTextLayout = onTextLayout
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = isbn,
                onValueChange = { isbn = it },
                label = { Text("ISBN", onTextLayout = onTextLayout) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {

                    val bookRepository = BookViewModel(navController, context)
                    bookRepository.saveBook(title.trim(), author.trim(), isbn.trim())
                    navController.navigate(ROUTE_VIEW_BOOK)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Book", onTextLayout = onTextLayout)
            }
            Spacer(modifier = Modifier.height(16.dp))
            //---------------------IMAGE PICKER START-----------------------------------//

            ImagePicker(Modifier,context, navController, title.trim(), author.trim(), isbn.trim())



        }

    }
}




@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, title:String, auther:String, isbn:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },      modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var bookRepository = BookViewModel(navController, context)
                bookRepository.saveBookWithImage(title, auther, isbn, imageUri!!)
                navController.navigate(ROUTE_VIEW_UPLOAD)

            },      modifier = Modifier.fillMaxWidth()) {
                Text(text = "Upload")
            }


        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddBooksScreenPreview(){
    AddBookScreen(rememberNavController())
}


