package com.example.todoapp.sign_up


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.R
import com.example.todoapp.Routes.SIGN_IN
import com.example.todoapp.components.CircleCanvas


@Composable
fun SignUp(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),

    ) {

    val navController = rememberNavController()

    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val confirmPassword = viewModel.confirmPassword.collectAsState()

    var showPasssword by remember {
        mutableStateOf(false)
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        CircleCanvas()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.size(90.dp))
            Text(
                "Welcome Onboard!",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black.copy(0.7f),
                modifier = Modifier.absolutePadding(top = 200.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                "Let’s help you to meet your Task!",
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                color = Color.Black.copy(0.7f),
            )
            Spacer(modifier = Modifier.size(70.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,

                        ),

                    singleLine = true,
                    value = email.value,
                    onValueChange = { viewModel.updateEmail(it) },
                    shape = RoundedCornerShape(28.dp),
                    label = {
                        Text(
                            text = "Email",
                            color = Color.Gray.copy(0.7f)
                        )
                    },
                    modifier = Modifier
                        .width(420.dp)
                        .height(60.dp)

                )

                Spacer(modifier = Modifier.size(30.dp))
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,

                        ),

                    visualTransformation = if (showPasssword) {
                        VisualTransformation.None

                    } else {
                        PasswordVisualTransformation()
                    },
                    singleLine = true,
                    value = password.value,
                    onValueChange = { viewModel.updatePassword(it) },
                    shape = RoundedCornerShape(28.dp),
                    label = {
                        Text(
                            text = "Password",
                            color = Color.Gray.copy(0.7f)
                        )
                    },
                    leadingIcon = {
                        if (showPasssword) {
                            IconButton(onClick = { showPasssword = false }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.visibility),
                                    contentDescription = null,
                                    tint = Color.Gray

                                )
                            }
                        } else {
                            IconButton(onClick = { showPasssword = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.visibility_off),
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .width(420.dp)
                        .height(60.dp)

                )
                Spacer(modifier = Modifier.size(30.dp))
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,

                        ),

                    singleLine = true,
                    value = confirmPassword.value,
                    onValueChange = { viewModel.updateConfirmPassword(it) },
                    shape = RoundedCornerShape(28.dp),
                    label = {
                        Text(
                            text = "Confirm Password",
                            color = Color.Gray.copy(0.7f)
                        )
                    },
                    modifier = Modifier
                        .width(420.dp)
                        .height(60.dp)

                )

            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.absoluteOffset(y = 80.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF50C2C9)),
                    shape = RoundedCornerShape(0.dp),
                    onClick = {
                        viewModel.onSignUpClick(openAndPopUp)

                    },
                    modifier = Modifier
                        .width(380.dp)
                        .height(80.dp)

                ) {
                    Text(
                        "Register",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                    Text(
                        text = "Already have Account ?",
                        fontSize = 20.sp,
                        color = Color.Black.copy(0.4f)
                    )
                    Text(
                        text = "Sign In",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF24D0C6)

                    )
            }
        }

    }
}

