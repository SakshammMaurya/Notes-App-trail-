package com.example.notes_combined.ui.theme


sealed interface NoteEvent {
    object SaveNote: NoteEvent
    data class setTitle(val title: String): NoteEvent
    data class setDescription(val description: String): NoteEvent

    object ShowDialog: NoteEvent
    object HideDialog: NoteEvent

    data class SortNotes(val sortType: SortType): NoteEvent
    data class DeleteNote(val note: Note): NoteEvent


}