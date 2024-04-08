package com.example.todoapp.addtask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.components.CircleCanvas


@Composable
fun AddTasks(
    taskId: String,
    popUpScreen: () -> Unit,
    restartApp: (String) -> Unit,
    viewModel: TasksAddViewModel = hiltViewModel(),
) {

    val task = viewModel.task.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initialize(taskId, restartApp)

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        CircleCanvas()
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Gray
                ),

                singleLine = true,
                value = task.value.title,
                onValueChange = { viewModel.onTitleChange(it) },
                shape = RoundedCornerShape(28.dp),
                label = {
                    Text(
                        text = "Title",
                        color = Color.Gray.copy(0.7f)
                    )
                },
                modifier = Modifier
                    .width(420.dp)
                    .height(60.dp)

            )
            Spacer(modifier = Modifier.height(50.dp))

        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            FloatingActionButton(
                onClick = { viewModel.saveTask(popUpScreen) },
                shape = CircleShape,
                containerColor = Color(0xFF50C2C9),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(40.dp)
            ) {
                Icon(Icons.Filled.Done, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Composable
fun PreviewAdd(
) {
    AddTasks(taskId = "1", {}, {})


}


