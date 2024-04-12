package com.example.notes_combined.ui.theme

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {
    @Upsert
    suspend fun upsertNote(note:Note)
    @Delete
    suspend fun deleteNote(note:Note)

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNotesOrderedByTitle(): Flow<List<Note>>


    @Query("SELECT * FROM note ORDER BY description ASC")
    fun getNotesOrderedByDescription(): Flow<List<Note>>


}