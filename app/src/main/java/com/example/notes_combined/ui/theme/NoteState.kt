package com.example.notes_combined.ui.theme

data class NoteState(
    val notes: List<Note> = emptyList(),
    val title: String= "",
    val description: String= "",
    val isAddingNotes: Boolean= false,
    val sortType: SortType= SortType.TITLE
)
