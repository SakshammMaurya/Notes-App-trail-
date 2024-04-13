package com.example.notes_combined.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NotesDao
):ViewModel(){
   private  val _sortType= MutableStateFlow(SortType.TITLE)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _notes = _sortType
        .flatMapLatest { sortTyoe->
            when(sortTyoe){
                SortType.TITLE->  dao.getNotesOrderedByTitle()
                SortType.DESCRIPTION->  dao.getNotesOrderedByDescription()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val  _state= MutableStateFlow(NoteState())
    val state= combine(_state,_sortType,_notes){ state,sortType,notes->
        state.copy(
            notes= notes,
            sortType=sortType
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NoteState())


   fun onEvent(event: NoteEvent){
       when(event){
           is NoteEvent.DeleteNote -> {
               viewModelScope.launch { dao.deleteNote(event.note) }
           }
           NoteEvent.HideDialog ->{
               _state.update { it.copy(
                   isAddingNotes = false
               ) }
           }
           NoteEvent.SaveNote -> {
               val title = state.value.title
               val description = state.value.description
               if(title.isBlank()) return

               val note= Note(
                   title= title,
                   description=description
               )
               viewModelScope.launch { dao.upsertNote(note) }
               _state.update { it.copy(
                   isAddingNotes = false,
                   title = "",
                   description = ""
               ) }
           }
           NoteEvent.ShowDialog -> {
               _state.update { it.copy(
                   isAddingNotes = true
               ) }
           }
           is NoteEvent.SortNotes -> {
               _sortType.value = event.sortType
           }
           is NoteEvent.setDescription -> {
               _state.update { it.copy(
                   description = event.description

               ) }
           }
           is NoteEvent.setTitle -> _state.update { it.copy(
               title= event.title

           ) }
       }
   }

}