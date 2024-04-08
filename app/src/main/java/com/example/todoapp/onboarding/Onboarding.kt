package com.example.todoapp.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.Routes.SIGN_UP
import com.example.todoapp.components.CircleCanvas


@Composable
fun Onboarding(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircleCanvas()
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .absolutePadding(bottom = 180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.people),
                contentDescription = "people",
                modifier = Modifier.size(230.dp)

            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .absoluteOffset(y = 120.dp)
                .absolutePadding(top = 80.dp),
        ) {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "Things To Do With TODO",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur \n" +
                            "adipiscing elit. Ullamcorper leo in eros \n" +
                            "parturient arcu odio diam. Gravida faucibus \n " +
                            "ac mauris et risus.",
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

            }


        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .absolutePadding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom,
            ) {
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF50C2C9)),
                shape = RoundedCornerShape(0.dp),
                onClick = {
                    viewModel.onAppStart(openAndPopUp)
                },
                modifier = Modifier
                    .width(380.dp)
                    .height(80.dp)

            ) {
                Text(
                    "Get Started",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(60.dp))

        }
    }

}


