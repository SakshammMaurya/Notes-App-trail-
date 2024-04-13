package com.example.notes_combined.ui.theme

import android.view.animation.OvershootInterpolator
import android.widget.GridLayout
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notes_combined.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController
) {
    val scale = remember{
        Animatable(0.6f)
    }

    LaunchedEffect(key1= true){
        scale.animateTo(
            targetValue = 1.1f,
            animationSpec= tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
        navController.navigate("NotesScreen")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
//            .background(Color(4279047220))
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .padding(top = 90.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = "Create",
                    color = Color(4294953472),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "and record",
                    color = Color(4294953472),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "your notes",
                    color = Color(4294953472),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "easily",
                    color = Color(4294953472),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        GradientButton(text ="Welcome!",
            textColor = Color.White,
            gradient = Brush.horizontalGradient(listOf(
                Color(4291393507),
                Color(0xFFEC57D0)

            )),
            onClick ={
//                LaunchedEffect(key1 = true){
//                    navController.navigate("NotesScreen")
//                }
//
            },
            navController
        )

        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.ylw),
            contentDescription = null,
            alignment = Alignment.BottomCenter,
            modifier=Modifier.scale(scale.value)

        )


    }

}

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit,
    navController: NavController

) {
    Button(
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .width(350.dp)
                .height(40.dp),
            contentAlignment = Alignment.Center

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

            ){
                Text(text = text, color = textColor,
                    fontSize = 22.sp,
                    modifier=Modifier.padding(start = 120.dp,end=60.dp))
                  Spacer(modifier = Modifier.width(30.dp))
//                Icon(imageVector = Icons.Default.ArrowForward,
//                    contentDescription = null,
//                    tint = Color(0xFFFFFFFF),
//                   )
            }

        }

    }


}