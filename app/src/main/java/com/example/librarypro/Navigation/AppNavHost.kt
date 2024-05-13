package com.example.librarypro.Navigation



import BorrowedBooksScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Screens.AuthScreens.ForgotPasswordScreen
import com.example.librarypro.Screens.AuthScreens.LoginScreen
import com.example.librarypro.Screens.AuthScreens.ResetPasswordScreen
import com.example.librarypro.Screens.AuthScreens.SignUpScreen
import com.example.librarypro.Screens.Books.AddBookScreen
import com.example.librarypro.Screens.Books.BorrowBooksForm
import com.example.librarypro.Screens.Books.FirstPage
import com.example.librarypro.Screens.Books.ReturnBooksScreen
import com.example.librarypro.Screens.Books.SearchBooksScreen
import com.example.librarypro.Screens.Books.UpdateBookScreen

import com.example.librarypro.Screens.Books.ViewBooksScreen
import com.example.librarypro.Screens.Books.ViewUploadsScreen
import com.example.librarypro.Screens.home.DashboardScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_GET_STARTED){
//    val (borrowedBooks, setBorrowedBooks) = remember { mutableStateOf(emptyList<BorrowedBook>()) }
    NavHost(navController = navController, modifier = Modifier, startDestination = startDestination ){

        composable(ROUTE_DASHBOARD){
            DashboardScreen(navController)
        }

        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_SIGNUP){
            SignUpScreen(navController)
        }
        composable(ROUTE_GET_STARTED){
            FirstPage(navController)
        }
        composable(ROUTE_RESET_PASSWORD){
            ResetPasswordScreen(navController)
        }
        composable(ROUTE_FORGOT_PASSWORD){
            ForgotPasswordScreen(navController)
        }
        composable(ROUTE_ADD_BOOK){
            AddBookScreen(navController)
        }
        composable(ROUTE_VIEW_BOOK){
            ViewBooksScreen(navController)
        }

//        composable(ROUTE_LIBRARIAN){
//            LibrarianScreen(navController)
//        }
        composable(ROUTE_BORROW_BOOK){
            BorrowBooksForm(navController)
        }
        composable(ROUTE_BORROWED_BOOKS){
            BorrowedBooksScreen(navController,)
        }
        composable(ROUTE_SEARCH_BOOK){
            SearchBooksScreen(navController)
        }
        composable(ROUTE_RETURN_BOOK){
            ReturnBooksScreen(navController)
        }
        composable(ROUTE_VIEW_UPLOAD){
            ViewUploadsScreen(navController)
        }



        composable(ROUTE_UPDATE_BOOK + "/{id}"){
            passedData ->
            UpdateBookScreen(
                navController,passedData.arguments?.getString("id")!!
            )
        }
    }
}




//
//fun BorrowBooksScreen(onBorrowBook: (String, String, String, String) -> Unit) {
//
//}

