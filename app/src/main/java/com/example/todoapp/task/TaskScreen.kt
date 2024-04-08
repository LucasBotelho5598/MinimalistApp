package com.example.todoapp.task

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.todoapp.components.CircleCanvas

@Composable
fun TaskScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: TaskScreenViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.initialize(restartApp)
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )


    val tasks by viewModel.tasks.collectAsState(emptyList())

    val myColor = Color(0xFF50C2C9)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(myColor)
        ) {
            CircleCanvas()
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .absolutePadding(top = 100.dp)

            ) {
                Spacer(modifier = Modifier.height(100.dp))


                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                        .border(2.dp, Color.White, CircleShape)
                        .clickable {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    model = selectedImageUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )


                /*Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ){
                FloatingActionButton(
                    onClick = {  },
                    shape = CircleShape,
                    containerColor = Color(0xFF50C2C9),
                    modifier = Modifier
                        .size(40.dp)
                ) {
                    Icon(Icons.Filled.Edit, contentDescription = null, tint = Color.White)
                }

                }*/
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Welcome Lucas Botelho",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White

                )

            }

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(24.dp)
        ) {
            Text(
                text = "Task List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black

            )

        }

        Column(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp)
                .align(Alignment.Center),

            ) {
            Spacer(modifier = Modifier.height(220.dp))

            Card(
                modifier = Modifier
                    .absolutePadding(top = 300.dp)
                    .fillMaxWidth()
                    .height(450.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "Task List",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    FloatingActionButton(
                        onClick = { viewModel.onAddClick(openScreen) },
                        shape = CircleShape,
                        containerColor = Color(0xFF50C2C9),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(40.dp)
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null, tint = Color.White)
                    }


                }

                LazyColumn(
                ) {
                    items(tasks, key = { it.id }) { taskItem ->
                        TaskItem(
                            task = taskItem,
                            onCheckChange = { viewModel.onTaskCheckChange(taskItem)  },
                            onTaskClick = {viewModel.onTaskClick(openScreen,taskItem)}


                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PreviewTaskScreen() {
    TaskScreen({}, {})
}