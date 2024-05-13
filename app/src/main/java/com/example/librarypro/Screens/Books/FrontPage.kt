package com.example.librarypro.Screens.Books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.librarypro.Components.HeadingTextComponent
import com.example.librarypro.Navigation.ROUTE_DASHBOARD
import com.example.librarypro.Navigation.ROUTE_SIGNUP
import com.example.librarypro.R
import kotlinx.coroutines.delay

//import dev.chrisbanes.accompanist.coil.rememberCoilPainter


@Composable
fun FirstPage(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.image7),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

HeadingTextComponent(heading = "Welcome to our library services!")


        Button(
            onClick = {
                navController.navigate(ROUTE_DASHBOARD)
            }, colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Get Started", color = Color.White)
        }
    }
}
//@Composable
//fun Carousel() {
//    val images = listOf(
////           "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bGlicmFyeXxlbnwwfHwwfHx8MA%3D%3D",
////        "https://media.istockphoto.com/id/1448121496/photo/young-woman-searching-books-in-the-library.webp?b=1&s=170667a&w=0&k=20&c=8gQ4uUHiCy-X1nF4DuoS27kMsHvtbMOQd9vUfiKAKsQ=",
////        "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bGlicmFyeXxlbnwwfHwwfHx8MA%3D%3D",
//        "https://media.istockphoto.com/id/1448121496/photo/young-woman-searching-books-in-the-library.webp?b=1&s=170667a&w=0&k=20&c=8gQ4uUHiCy-X1nF4DuoS27kMsHvtbMOQd9vUfiKAKsQ=",
//    "https://plus.unsplash.com/premium_photo-1683134602059-c8c84737f0c9?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8bGlicmFyeXxlbnwwfHwwfHx8MA%3D%3D",
//        "https://media.istockphoto.com/id/1984345363/photo/low-angle-view-of-books-on-shelves-in-long-room-of-trinity-college-old-library-in-dublin.webp?b=1&s=170667a&w=0&k=20&c=Fq4LJqGl_KihwZbVZUFXno_UynvM_GDdpYln3Slfu4U=",
//        "https://plus.unsplash.com/premium_photo-1683134602059-c8c84737f0c9?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8bGlicmFyeXxlbnwwfHwwfHx8MA%3D%3D",
//        "https://media.istockphoto.com/id/1984345363/photo/low-angle-view-of-books-on-shelves-in-long-room-of-trinity-college-old-library-in-dublin.webp?b=1&s=170667a&w=0&k=20&c=Fq4LJqGl_KihwZbVZUFXno_UynvM_GDdpYln3Slfu4U=",
//        "https://plus.unsplash.com/premium_photo-1683134602059-c8c84737f0c9?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8bGlicmFyeXxlbnwwfHwwfHx8MA%3D%3D",
//        "https://media.istockphoto.com/id/1984345363/photo/low-angle-view-of-books-on-shelves-in-long-room-of-trinity-college-old-library-in-dublin.webp?b=1&s=170667a&w=0&k=20&c=Fq4LJqGl_KihwZbVZUFXno_UynvM_GDdpYln3Slfu4U=",
//        "https://plus.unsplash.com/premium_photo-1683134602059-c8c84737f0c9?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8bGlicmFyeXxlbnwwfHwwfHx8MA%3D%3D",
//        "https://media.istockphoto.com/id/1984345363/photo/low-angle-view-of-books-on-shelves-in-long-room-of-trinity-college-old-library-in-dublin.webp?b=1&s=170667a&w=0&k=20&c=Fq4LJqGl_KihwZbVZUFXno_UynvM_GDdpYln3Slfu4U=",
//        )
//
//    var currentIndex by remember { mutableIntStateOf(0) }
//
//    LaunchedEffect(true) {
//        while (true) {
//            delay(3000) // Change image every 3 seconds
//            currentIndex = (currentIndex + 1) % images.size
//        }
//    }
//
//    LazyRow {
//        items(images) { imageUrl ->
//            Image(
//                painter = rememberImagePainter(imageUrl),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(200.dp)
//                    .padding(4.dp)
//            )
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun FrontPagePreview(){
    FirstPage(rememberNavController())
}
