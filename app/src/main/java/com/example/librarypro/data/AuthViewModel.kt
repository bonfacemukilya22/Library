package com.example.librarypro.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.librarypro.Navigation.ROUTE_DASHBOARD
import com.example.librarypro.Navigation.ROUTE_LOGIN
import com.example.librarypro.Navigation.ROUTE_SIGNUP
import com.example.librarypro.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AuthViewModel(var navController: NavController, var context: Context) {
    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("PLease Wait a moment.....")
    }


    fun SignUp(email: String, fullName: String, password: String, phoneNumber: String) {
//         Validate input fields
        if (fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && phoneNumber.isNotBlank()) {
            // Call Firebase to create a user with email and password
            progress.show()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(context,"Succeffully Registered!",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_DASHBOARD)}
//
                            // Sign up success, navigate to next screen or do something else
                         else {
                            // Sign up failed, display an error message
                            Toast.makeText(context, "Sign up failed. ${it.exception!!.message}", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_SIGNUP)
                        }
                    }


                }

        else{
            Toast.makeText(context, "Please fill in all the fields!", Toast.LENGTH_LONG).show()
        }

    }



    fun Login(email: String, password: String, ) {
        progress.show()
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Succeffully Logged in",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD)
//                navController.navigate(ROUTE_REGISTER)TO TAKE YOU TO A DIIFFERNT PAGE
            }else{
                Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }
    }
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }

    fun isloggedin():Boolean{
        return mAuth.currentUser !=null
    }

    fun ResetPass(email: String){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    // Password reset email sent successfully
                    Toast.makeText(context,"Password reset email sent successfully",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_LOGIN)
                } else {
                    // Password reset email could not be sent
                    Toast.makeText(context,"Password reset email could not be sent",Toast.LENGTH_LONG).show()
                }
            }
//
    }
}
