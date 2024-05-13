package com.example.librarypro.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarypro.ui.theme.Tertirary


@Composable
fun ImageComponent(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp)
    )
}

@Composable
fun HeadingTextComponent(heading: String) {
    val onTextLayout:(TextLayoutResult)->Unit={}
    Text(
        text = heading,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 25.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
        onTextLayout=onTextLayout
    )
}
//
@Composable
fun ForgotPasswordHeadingTextComponent(action: String) {
    val onTextLayout: (TextLayoutResult) -> Unit = {}
    Column {
        Text(
            text = action,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 39.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,onTextLayout=onTextLayout
        )
        Text(
            text = "Password?",
            modifier = Modifier.fillMaxWidth().offset(y = (-18).dp),
            fontSize = 39.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,onTextLayout=onTextLayout
        )
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyTextField(labelVal: String,
////                icon: Int
//) {
//    val onTextLayout: (TextLayoutResult) -> Unit = {}
//    var textVal by remember {
//        mutableStateOf("")
//    }
//    val typeOfKeyboard: KeyboardType = when (labelVal) {
//        "Enter email" -> KeyboardType.Email
//        "Enter phoneNumber" -> KeyboardType.Phone
//        else -> KeyboardType.Text
//    }
//
//    OutlinedTextField(
//        value = textVal,
//        onValueChange = {
//            textVal = it
//        },
//        modifier = Modifier.fillMaxWidth(),
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedTextColor = Color.Black,
//            focusedBorderColor = BrandColor,
//            unfocusedBorderColor = BorderColor,
//            focusedLeadingIconColor = BrandColor,
//            unfocusedLeadingIconColor = Tertirary,
//        ),
//        shape = MaterialTheme.shapes.small,
//        placeholder = {
//            Text(text = labelVal, color = Color.Black,onTextLayout=onTextLayout)
//        },
////        leadingIcon = {
////            Icon(
////                painter = painterResource(id = icon),
////                contentDescription = "at_symbol"
////            )
////        },
//        keyboardOptions = KeyboardOptions(
//            keyboardType = typeOfKeyboard,
//            imeAction = ImeAction.Done
//        ),
//        singleLine = true
//    )
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PasswordInputComponent(labelVal: String) {
//    val onTextLayout: (TextLayoutResult) -> Unit = {}
//    var password by remember {
//        mutableStateOf("")
//    }
//    var isShowPassword by remember {
//        mutableStateOf(false)
//    }
//    OutlinedTextField(
//        value = password,
//        onValueChange = {
//            password = it
//        },
//        modifier = Modifier.fillMaxWidth(),
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedTextColor = Color.Black,
//            focusedBorderColor = BrandColor,
//            unfocusedBorderColor = BorderColor,
//        ),
//        shape = MaterialTheme.shapes.small,
//        placeholder = {
//            Text(text = labelVal, color = Color.Black, onTextLayout = onTextLayout)
//        },
////        leadingIcon = {
//            Icon(
//                painter = painterResource(id = R.drawable.lock),
//                contentDescription = "at_symbol",
//                tint = Tertirary
//            )
//        },
//        trailingIcon = {
//            val description = if (isShowPassword) "Show Password" else "Hide Password"
//            val iconImage =
//                if (isShowPassword) R.drawable.pheyeclosedfill__1_ else R.drawable.eye_closed
//            IconButton(onClick = {
//                isShowPassword = !isShowPassword
//            }) {
//                Icon(
//                    painter = painterResource(id = iconImage),
//                    contentDescription = description,
//                    tint = Tertirary,
//                )
//            }
//        },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
//    )
//}
//
//@Composable
//fun ForgotPasswordTextComponent(navController: NavHostController) {
//    val onTextLayout: (TextLayoutResult) -> Unit = {}
//    Text(
//        text = "Forgot Password?",
//        color = BrandColor,
//        fontWeight = FontWeight.Bold,
//        fontSize = 20.sp,
//        modifier = Modifier.clickable {
//            navController.navigate(ROUTE_FORGOT_PASSWORD)
//        },onTextLayout=onTextLayout
//    )
//}
//@Composable
//fun MyButton2(labelVal: String, navController: NavHostController) {
//    val onTextLayout: (TextLayoutResult) -> Unit = {}
//    Button(
//        onClick = {
////            val myregister = AuthViewModel(navController,context)
//
////            myregister.SignUp(email.text.trim(), Password.text.trim(),
////                fullName.text.trim(), mobile.text.trim())
//            navController.navigate(ROUTE_LOGIN)
//        },
//        colors = ButtonDefaults.buttonColors(
//            containerColor = BrandColor
//        ),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 40.dp)
//    ) {
//        Text(
//            text = labelVal,
//            color = Color.White,
//            fontSize = 18.sp,
//            modifier = Modifier.clickable {
//                if (labelVal == "Submit") {
//                    navController.navigate(ROUTE_LOGIN)
//                }
//            },onTextLayout=onTextLayout
//        )
//    }
//}
//
//@Composable
//fun MyButton1(labelVal: String, navController: NavHostController) {
//    val onTextLayout: (TextLayoutResult) -> Unit = {}
//    Button(
//        onClick = {
////            val myregister = AuthViewModel(navController,context)
//
////            myregister.SignUp(email.text.trim(), Password.text.trim(),
////                fullName.text.trim(), mobile.text.trim())
//            navController.navigate(ROUTE_RESET_PASSWORD)
//        },
//        colors = ButtonDefaults.buttonColors(
//            containerColor = BrandColor
//        ),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 40.dp)
//    ) {
//        Text(
//            text = labelVal,
//            color = Color.White,
//            fontSize = 18.sp,
//            modifier = Modifier.clickable {
//                if (labelVal == "Submit") {
//                    navController.navigate("ResetPassword")
//                }
//            },onTextLayout=onTextLayout
//        )
//    }
//}
//
//@Composable
//fun MyButton(labelVal: String, navController: NavHostController) {
//    val onTextLayout: (TextLayoutResult) -> Unit = {}
//    Button(
//        onClick = {
////            val myregister = AuthViewModel(navController,context)
//
////            myregister.SignUp(email.text.trim(), Password.text.trim(),
////                fullName.text.trim(), mobile.text.trim())
//            navController.navigate(ROUTE_LOGIN)
//        },
//        colors = ButtonDefaults.buttonColors(
//            containerColor = BrandColor
//        ),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 40.dp)
//    ) {
//        Text(
//            text = labelVal,
//            color = Color.White,
//            fontSize = 18.sp,
//            modifier = Modifier.clickable {
//                if (labelVal == "Submit") {
//                    navController.navigate(ROUTE_RESET_PASSWORD)
//                }
//            }, onTextLayout = onTextLayout
//        )
//    }
//}

//@Composable
//fun BottomComponent(navController: NavHostController) {
//    Column {
//        val onTextLayout: (TextLayoutResult) -> Unit = {}
//        MyButton(labelVal = "Continue", navController = navController)
//        Spacer(modifier = Modifier.height(10.dp))
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Divider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f),
//                thickness = 1.dp,
//                color = Tertirary
//            )
//            Text(
//                text = "OR",
//                modifier = Modifier.padding(10.dp),
//                color = Tertirary,
//                fontSize = 20.sp, onTextLayout = onTextLayout
//            )
//            Divider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f),
//                thickness = 1.dp,
//                color = Tertirary
//            )
//        }
//        Spacer(modifier = Modifier.height(5.dp))
//        Button(
//            onClick = {
//                      navController.navigate(ROUTE_DASHBOARD)
//                      },
//            modifier = Modifier
//                .fillMaxWidth(),
//            colors = ButtonDefaults.outlinedButtonColors(
//                containerColor = BgSocial
//            )
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
////                Image(
////                    painter = painterResource(id = R.drawable.icong),
////                    contentDescription = "google icon"
////                )
//                Text(
//                    text = "Login With Google",
//                    fontSize = 18.sp,
//                    color = Tertirary,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth(), onTextLayout = onTextLayout
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomLoginTextComponent(initialText: String, action: String, navController: NavHostController) {
//    val annotatedString = buildAnnotatedString {
//        withStyle(style = SpanStyle(color = Color.Blue,)) {
//            append(initialText)
//        }
//        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
//            pushStringAnnotation(tag = action, annotation = action)
//            append(action)
//        }
//    }
//
//    ClickableText(text = annotatedString, onClick = {
//        annotatedString.getStringAnnotations(it, it)
//            .firstOrNull()?.also { span ->
//                Log.d("BottomLoginTextComponent", "${span.item} is Clicked")
//                if (span.item == "Join our app!") {
//                    navController.navigate(ROUTE_SIGNUP)
//                }
//            }
//    })
//}

//@Composable
//fun SignupTermsAndPrivacyText() {
//    val initialText = "Accept our "
//    val termsNConditionText = "Terms & Conditions"
//    val andText = " and "
//    val privacyPolicyText = "Privacy Policy."
//    val lastText = " Don't be afraid, we don't bite!"
//
//    val annotatedString = buildAnnotatedString {
//        withStyle(style = SpanStyle(color = Tertirary)) {
//            append(initialText)
//        }
//        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
//            pushStringAnnotation(tag = termsNConditionText, annotation = termsNConditionText)
//            append(termsNConditionText)
//        }
//        withStyle(style = SpanStyle(color = Tertirary)) {
//            append(andText)
//        }
//        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
//            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
//            append(privacyPolicyText)
//        }
//        withStyle(style = SpanStyle(color = Tertirary)) {
//            append(lastText)
//        }
//    }
//
//    ClickableText(text = annotatedString, onClick = {
//        annotatedString.getStringAnnotations(it, it)
//            .firstOrNull()?.also { span ->
//                Log.d("SignupTermsAndPrivacyText", span.item)
//            }
//    })
//}
//
////
//@Composable
//fun BottomSignupTextComponent(navController: NavHostController) {
//    val initialText = "Have an account ? "
//    val loginText = "Log In"
//    val lastText = " again and enjoy our services!"
//
//    val annotatedString = buildAnnotatedString {
//        withStyle(style = SpanStyle(color = Tertirary)) {
//            append(initialText)
//        }
//        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
//            pushStringAnnotation(tag = loginText, annotation = loginText)
//            append(loginText)
//        }
//        withStyle(style = SpanStyle(color = Tertirary)) {
//            append(lastText)
//        }
//    }
//
//    ClickableText(text = annotatedString, onClick = {
//        annotatedString.getStringAnnotations(it, it)
//            .firstOrNull()?.also { span ->
//                if (span.item == "Log In") {
//                    navController.navigate(ROUTE_LOGIN)
//                }
//            }
//    })
//
//}

@Composable
fun TextInfoComponent(textVal: String) {
    val onTextLayout: (TextLayoutResult) -> Unit = {}
    Text(text = textVal, color = Tertirary, onTextLayout = onTextLayout)
}