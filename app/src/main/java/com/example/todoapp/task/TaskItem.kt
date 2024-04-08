package com.example.todoapp.task


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.model.Task


@Composable
fun TaskItem(
    task: Task,
    onTaskClick: () -> Unit,
    onCheckChange: () -> Unit,
    viewModel: TaskScreenViewModel = hiltViewModel(),
    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
            .clickable { onTaskClick() }

        ) {
        Checkbox(
            checked = task.completed,
            onCheckedChange = { onCheckChange() },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF50C2C9),
                checkmarkColor = Color.White,
                uncheckedColor = Color(0xFF50C2C9)

            ),
        )
            Text(
                text = task.title,
                textDecoration = if (task.completed) {
                    TextDecoration.LineThrough
                } else {
                    null
                }
            )



        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { viewModel.onDeleteClick(task) },
            modifier = Modifier.padding(end = 12.dp)
        )
        { Icon(Icons.Filled.Delete, contentDescription = null, tint = Color(0xFF50C2C9)) }
    }
}


