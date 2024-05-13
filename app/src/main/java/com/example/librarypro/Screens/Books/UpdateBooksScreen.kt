package com.example.librarypro.Screens.Books

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Components.ImageComponent
import com.example.librarypro.Navigation.ROUTE_VIEW_BOOK
import com.example.librarypro.R
import com.example.librarypro.data.BookViewModel
import com.example.librarypro.model.Books
import com.example.librarypro.ui.theme.BgSocial
import com.example.librarypro.ui.theme.Tertirary
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun UpdateBookScreen(navController: NavHostController, id: String, ) {

        Column(modifier = Modifier
                .padding(16.dp)
                .background(BgSocial)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var title by remember { mutableStateOf("") }
            var author by remember { mutableStateOf("") }
            var isbn by remember { mutableStateOf("") }
            var context = LocalContext.current

            var currentDataRef = FirebaseDatabase.getInstance().getReference()
                .child("books/$id")
            currentDataRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var book = snapshot.getValue(Books::class.java)
                    title = book!!.title
                    author = book!!.auther
                    isbn = book!!.isbn
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
            ImageComponent(image = R.drawable.image9)
            var bookTitle by remember { mutableStateOf(TextFieldValue(title)) }
            var BookAuther by remember { mutableStateOf(TextFieldValue(author)) }
            var bookIsbn by remember { mutableStateOf(TextFieldValue(isbn)) }

            val onTextLayout: (TextLayoutResult) -> Unit = {}
            OutlinedTextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                label = { Text("Title",onTextLayout=onTextLayout) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = BookAuther,
                onValueChange = { BookAuther = it },
                label = { Text("Author",onTextLayout=onTextLayout) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = bookIsbn,
                onValueChange = { bookIsbn = it },
                label = { Text("ISBN",onTextLayout=onTextLayout) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
//                    //-----------WRITE THE UPDATE LOGIC HERE---------------//

                    var bookRepository = BookViewModel(navController,context)
                    bookRepository.updateBook(title.trim(),author.trim(), isbn.trim(), id)
                    navController.navigate(ROUTE_VIEW_BOOK)

                 }, colors = ButtonDefaults.buttonColors(Tertirary),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            {
                Text("Update Book",onTextLayout=onTextLayout)
            }
        }
    }


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UpdateBooksScreenPreview(){
    UpdateBookScreen(rememberNavController(), id = "")
}