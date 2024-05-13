package com.example.librarypro.Screens.Books

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Components.ImageComponent
import com.example.librarypro.R
import com.example.librarypro.data.BookViewModel
import com.example.librarypro.model.Books
import com.google.firebase.database.FirebaseDatabase



@Composable
fun BorrowBooksForm(
    navController: NavHostController,


) {
    val bookTitleState = remember { mutableStateOf("") }
    val authorState = remember { mutableStateOf("") }
    val isbnState = remember { mutableStateOf("") }
    val borrowDateState = remember { mutableStateOf("") }
    val errorMessageState = remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        ImageComponent(image = R.drawable.image3)
        TextField(
            value = bookTitleState.value,
            onValueChange = { bookTitleState.value = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = authorState.value,
            onValueChange = { authorState.value = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = isbnState.value,
            onValueChange = { isbnState.value = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = borrowDateState.value,
            onValueChange = { borrowDateState.value = it },
            label = { Text("Date of Book Borrowed") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),

            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessageState.value ?: "",
            color = Color.Red,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val bookRepo = BookViewModel(navController, context)
                val borrowedBooksRef = FirebaseDatabase.getInstance().getReference("borrowedBooks")
                val bookTitle = "Book Title" // Replace with the actual book title retrieved from your UI
                bookRepo.borrowBook(bookTitle, borrowedBooksRef) { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Borrow Book")
        }




    }
}

@Preview(showBackground = true)
@Composable
fun BorrowBooksScreenPreview() {
    BorrowBooksForm(rememberNavController())
}


