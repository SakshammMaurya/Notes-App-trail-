package com.example.notes_combined.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddNoteDialog(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit,
    modifier: Modifier = Modifier
) {


    AlertDialog(
        modifier = modifier,
        title = { Text(text = "Add Note") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value =state.title,
                    onValueChange ={
                        onEvent(NoteEvent.setTitle(it))
                    },
                    placeholder = {
                        Text(text = "title")
                    }
                )

                TextField(
                    value =state.description,
                    onValueChange ={
                        onEvent(NoteEvent.setDescription(it))
                    },
                    placeholder = {
                        Text(text = "description")
                    }
                )

            }
        },
        onDismissRequest = {
            onEvent(NoteEvent.HideDialog)
        },
        confirmButton = {
            Box(modifier= Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                Button(onClick = {
                    onEvent(NoteEvent.SaveNote)
                }) {
                    Text(text = "Save Note")
                }
            }
        }
    )








}