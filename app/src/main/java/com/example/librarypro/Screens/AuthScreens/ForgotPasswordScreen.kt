package com.example.librarypro.Screens.AuthScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.librarypro.Components.ForgotPasswordHeadingTextComponent
import com.example.librarypro.Components.ImageComponent
import com.example.librarypro.Components.TextInfoComponent
import com.example.librarypro.Navigation.ROUTE_RESET_PASSWORD
import com.example.librarypro.R
import com.example.librarypro.ui.theme.BgSocial
import com.example.librarypro.ui.theme.Tertirary


@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    Surface(color = Color.Green) {
        Column(  modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            var email by remember { mutableStateOf("") }
            Column {
                val onTextLayout: (TextLayoutResult) -> Unit = {}
                ImageComponent(image = R.drawable.image1)
                Spacer(modifier = Modifier.height(10.dp))
//                ForgotPasswordHeadingTextComponent(action = "Forgot Password?")
                Text(
                    text = "Forgot your password?",
                    color = Color.Blue,
                    fontSize = 30.sp,
                    textDecoration = TextDecoration.Underline,
                    onTextLayout = onTextLayout,
                    fontFamily = FontFamily.Cursive
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextInfoComponent(
                    textVal = "Don't worry, strange things happen. Please enter the email address associated with your account ."
                )
                Spacer(modifier = Modifier.height(20.dp))

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


//            Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        navController.navigate(ROUTE_RESET_PASSWORD)
                    },
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        Tertirary
                    )
                ) {
                    Text(text = "Submit", onTextLayout = onTextLayout)
                }

            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgotPasswordPreviewScreen(){
    ForgotPasswordScreen(rememberNavController())
}