package com.example.notes_combined.ui.theme

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun NotesScreen(
    navController: NavController,
    state: NoteState,
    onEvent: (NoteEvent)->Unit
) {


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(NoteEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )
            }
        },

        topBar = {
            dropDown(
                text = "NOTES APP",
                content = {
                    Text(
                        text = "Sort",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.clickable {
                            onEvent(NoteEvent.SortNotes(SortType.TITLE))
                        }
                            .fillMaxWidth()

                    )
                },
                onEvent = onEvent, state = NoteState(),
            )
        },
        containerColor = Color(0xFF000000)

    ) { padding ->
        if (state.isAddingNotes) {
            AddNoteDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                items(state.notes) { note ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start=12.dp,end=12.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(4294957644)),
                        contentAlignment = Alignment.Center,

                        ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp),

                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = " ${note.title}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(4285552640),
                               // modifier=Modifier.padding(start=8.dp)
                            )
                            Text(
                                text = " ${note.description}",
                                fontSize = 16.sp,
                                color = Color(4294966744),
                               // modifier=Modifier.padding(start=8.dp,bottom=4.dp)
                            )
                        }
                        IconButton(onClick = { onEvent(NoteEvent.DeleteNote(note)) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Contact",
                                tint = Color(4294966744)
                            )
                        }
                    }
                }
                }
            })

    }
}

@Composable
fun NoteItem(state: NoteState, index: Int, onEvent: (NoteEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(4291397350))

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.notes.get(index=index).title,
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = state.notes.get(index=index).description,
                fontSize = 16.sp,
                color = Color.White
            )
        }
        IconButton(onClick = { onEvent(NoteEvent.DeleteNote(
            state.notes.get(index=index)
        )) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Notes"
            )
        }

    }

}

@Composable
fun dropDown(
    text: String,
    modifier: Modifier=Modifier,
    initiallyOpened:Boolean = false,
    content: @Composable ()-> Unit,
    onEvent: (NoteEvent) -> Unit,
    state:NoteState
){
     var isOpen by remember {
         mutableStateOf(initiallyOpened)
     }
    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        ), label = ""
    )
    val rotateX = animateFloatAsState(
        targetValue = if (isOpen) 0f else -90f,
        animationSpec = tween(
            durationMillis = 300
        ), label = ""
    )

    Box(
        modifier=modifier.padding(12.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier=modifier.fillMaxWidth()
               ,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                modifier=Modifier.fillMaxWidth().padding(2.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .height(50.dp)
                    .background(Color(4291467743)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ){
                Text(text = text,
                    color= Color.White,
                    fontSize = 18.sp,
                    modifier=Modifier.padding(8.dp)
                )
                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Sort Type",
                    tint= Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            isOpen = !isOpen
                        }
                        .scale(1f, if (isOpen) -1f else 1f)
                )

            }
            Spacer(modifier = Modifier.height(5.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier= Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(80.dp)
                    .height(30.dp)
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(0.5f, 0f)
                        rotationX = rotateX.value
                    }
                    .alpha(alpha.value)
                    .background(Color.White)
                    .align(Alignment.End)

            ){
                content()
            }
        }

    }

}
