package com.example.librarypro.Screens.Books

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Components.ImageComponent
import com.example.librarypro.Navigation.ROUTE_DASHBOARD
import com.example.librarypro.R
import com.example.librarypro.ui.theme.BgSocial


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnBooksScreen(navController: NavHostController){
    ReturnBooksForm { bookTitle, author, isbn, returnDate ->
        // Handle the book return action
        // For example, you can print the details to the log
        println("Returning book: $bookTitle by $author (ISBN: $isbn), Return Date: $returnDate")
    }
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


@SuppressLint("RememberReturnType")
@Composable
fun ReturnBooksForm(onReturnBook: (String, String, String,String) -> Unit) {
    val bookTitleState = remember { mutableStateOf(TextFieldValue()) }
    val authorState = remember { mutableStateOf(TextFieldValue()) }
    val isbnState = remember { mutableStateOf(TextFieldValue()) }
    val returnDateState = remember { mutableStateOf(TextFieldValue()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(BgSocial)
    ) {
        ImageComponent(image = R.drawable.image7)
        val onTextLayout: (TextLayoutResult) -> Unit = {}
        TextField(
            value = bookTitleState.value,
            onValueChange = { bookTitleState.value = it },
            label = { Text("Book Title", onTextLayout = onTextLayout) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = authorState.value,
            onValueChange = { authorState.value = it },
            label = { Text("Author", onTextLayout = onTextLayout) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = isbnState.value,
            onValueChange = { authorState.value = it },
            label = { Text("ISBN", onTextLayout = onTextLayout) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = returnDateState.value,
            onValueChange = { returnDateState.value = it },
            label = { Text("Return Date", onTextLayout = onTextLayout) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                // Handle done action, e.g., submit the form
                onReturnBook(
                    bookTitleState.value.text,
                    authorState.value.text,
                    isbnState.value.text,
                    returnDateState.value.text
                )
            }),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Handle button click, e.g., submit the form
                onReturnBook(
                    bookTitleState.value.text,
                    authorState.value.text,
                    isbnState.value.text,
                    returnDateState.value.text
                )
            },
            modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
        ) {
            Text(text = "Return Book", onTextLayout = onTextLayout)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReturnBooksScreenPreview() {
ReturnBooksScreen(rememberNavController())
}
