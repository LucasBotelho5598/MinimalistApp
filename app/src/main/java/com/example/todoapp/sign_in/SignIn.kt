package com.example.todoapp.sign_in


import android.graphics.drawable.Icon
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.R
import com.example.todoapp.components.CircleCanvas
import com.example.todoapp.ui.theme.TodoAppTheme
import com.google.firebase.auth.FirebaseAuth



@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()

    var showPasssword by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val myColor = Color(0xFF24D0C6)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        CircleCanvas()
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                "Welcome Back!",
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp,
                color = Color.Black.copy(0.7f),
                modifier = Modifier
                    .absolutePadding(top = 200.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(id = R.drawable.sign),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 70.dp)

            )
            Spacer(modifier = Modifier.height(90.dp))
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
            Spacer(modifier = Modifier.height(35.dp))
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
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                "Forgot Password?",
                fontWeight = FontWeight.Light,
                fontSize = 22.sp,
                color = Color(myColor.value)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF50C2C9)),
                shape = RoundedCornerShape(0.dp),
                onClick = {
                    viewModel.onSignInClick(openAndPopUp)
                    if (email.value.isEmpty() && password.value.isEmpty()) {
                        Toast.makeText(context, "Inválido", Toast.LENGTH_LONG).show()
                    } else {
                        FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(email.value, password.value)
                            .addOnCompleteListener { Task ->
                                if (Task.isSuccessful) {
                                    Toast.makeText(context, "Successful", Toast.LENGTH_LONG)
                                        .show()
                                } else {
                                    Toast.makeText(context, "Wrong", Toast.LENGTH_LONG).show()

                                }
                            }
                    }
                },
                modifier = Modifier
                    .width(380.dp)
                    .height(80.dp)

            ) {
                Text(
                    "Sign In",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = { viewModel.onSignUpClick(openAndPopUp) }) {
                    Text(
                        text = "Dont’t have Account ?",
                        fontSize = 20.sp,
                        color = Color.Black.copy(0.4f)
                    )
                    Text(
                        text = "Sign Up",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF24D0C6)

                    )
                }


            }

        }

    }

}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun PreviewSignIn() {
    TodoAppTheme {
        SignIn({ _, _ -> })
    }

}