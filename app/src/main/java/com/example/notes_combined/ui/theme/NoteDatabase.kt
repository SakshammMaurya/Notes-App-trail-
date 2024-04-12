package com.example.notes_combined.ui.theme

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities=[Note::class],
    version= 1
)

abstract  class NoteDatabase ():RoomDatabase(){
    abstract val dao: NotesDao
}