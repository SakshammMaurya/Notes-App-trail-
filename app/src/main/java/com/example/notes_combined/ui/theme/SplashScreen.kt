package com.example.notes_combined.ui.theme

import android.widget.GridLayout
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notes_combined.R


@Composable
fun SplashScreen(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(4279047220))
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
                    color = Color(4291397350),
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "and record",
                    color = Color(4291397350),
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "your notes",
                    color = Color(4291397350),
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "easily",
                    color = Color(4291397350),
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(2.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        GradientButton(text ="Welcome!",
            textColor = Color.White,
            gradient = Brush.horizontalGradient(listOf(
                Color(4291397350),
                Color(4293616849)

            )),
            onClick ={
                navController.navigate("AddNoteScreen")

            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.vc),
            contentDescription = null,
            alignment = Alignment.BottomCenter

        )


    }

}

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit,

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
                .height(35.dp),
            contentAlignment = Alignment.Center

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

            ){
                Text(text = text, color = textColor,
                    fontSize = 22.sp,
                    modifier=Modifier.padding(start = 120.dp,end=60.dp))
                  Spacer(modifier = Modifier.width(30.dp))
                Icon(imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFFFFFFFF),
                   )
            }

        }

    }


}