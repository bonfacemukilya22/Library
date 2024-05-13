package com.example.librarypro.Screens.AuthScreens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Components.HeadingTextComponent
import com.example.librarypro.Components.ImageComponent
import com.example.librarypro.Navigation.ROUTE_FORGOT_PASSWORD
import com.example.librarypro.Navigation.ROUTE_RESET_PASSWORD
import com.example.librarypro.Navigation.ROUTE_SIGNUP
import com.example.librarypro.R
import com.example.librarypro.data.AuthViewModel
import com.example.librarypro.ui.theme.BgSocial
import com.example.librarypro.ui.theme.Tertirary


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isShowPassword by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Surface(color = Color.Green) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
//        .background(BgSocial)


        ) {
            val onTextLayout: (TextLayoutResult) -> Unit = {}
            Text(
                text = "Have an account sign in here",
                color = Color.Blue,
                fontSize = 30.sp,
                textDecoration = TextDecoration.Underline,
                onTextLayout = onTextLayout,
                fontFamily = FontFamily.Cursive
            )
            ImageComponent(image = R.drawable.image5)

//        val onTextLayout: (TextLayoutResult) -> Unit = {}
//        HeadingTextComponent(heading = "Login here")


            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Enter email", onTextLayout = onTextLayout) },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Enter password", onTextLayout = onTextLayout) },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val myloginlogic = AuthViewModel(navController, context)
                    myloginlogic.Login(email.trim(), password.trim())

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Tertirary)
            ) {
                Text(text = "Login", onTextLayout = onTextLayout)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Have an account? Sign up", modifier = Modifier.clickable {
                navController.navigate(
                    ROUTE_SIGNUP
                )
            }, color = Color.Blue)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "forgot password?", modifier = Modifier.clickable {
                navController.navigate(
                    ROUTE_FORGOT_PASSWORD
                )
            }, color = Color.Blue)


        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}