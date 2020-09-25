package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(application: Application?) {
    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note>?>?

    /*Applo es una clase de contexto para crear la ddbb en el contexto*/
    init {
        val database = NoteDatabase.getInstance(application!!)
        noteDao = database!!.notesDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note?) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note?) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    //    hay que crear un asynctask para cada metodo
    fun delete(note: Note?) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    /*no consrva el puntero a la misma clase y se evitan memory leaks*/ /*se le debn pasar los tres tipos al AsycTask solo notes por eso se le pone Void*/
    private class InsertNoteAsyncTask(
            private val noteDao: NoteDao
    ) : AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.insert(notes[0])
            return null
        }
    }

    private class UpdateNoteAsyncTask(
            private val noteDao: NoteDao
    ) : AsyncTask<Note?, Void?, Void?>() {
        protected override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.update(notes[0])
            return null
        }
    }

    private class DeleteNoteAsyncTask(
            private val noteDao: NoteDao
    ) : AsyncTask<Note?, Void?, Void?>() {
        protected override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.delete(notes[0])
            return null
        }
    }

    private class DeleteAllNotesAsyncTask internal constructor(
            private val noteDao: NoteDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            noteDao.deleteAllNotes()
            return null
        }

    }

}