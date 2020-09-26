package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class GestionRepository(application: Application?) {
    private val noteDao: GestionDao
    val allNotes: LiveData<List<Clientes_Entity>?>?

    /*Applo es una clase de contexto para crear la ddbb en el contexto*/
    init {
        val database = GestionDatabase.getInstance(application!!)
        noteDao = database!!.notesDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Clientes_Entity?) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Clientes_Entity?) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    //    hay que crear un asynctask para cada metodo
    fun delete(note: Clientes_Entity?) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    /*no consrva el puntero a la misma clase y se evitan memory leaks*/ /*se le debn pasar los tres tipos al AsycTask solo notes por eso se le pone Void*/
    private class InsertNoteAsyncTask(
        private val noteDao: GestionDao
    ) : AsyncTask<Clientes_Entity?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Clientes_Entity?): Void? {
            noteDao.insert(notes[0])
            return null
        }
    }

    private class UpdateNoteAsyncTask(
        private val noteDao: GestionDao
    ) : AsyncTask<Clientes_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg notes: Clientes_Entity?): Void? {
            noteDao.update(notes[0])
            return null
        }
    }

    private class DeleteNoteAsyncTask(
        private val noteDao: GestionDao
    ) : AsyncTask<Clientes_Entity?, Void?, Void?>() {
        protected override fun doInBackground(vararg notes: Clientes_Entity?): Void? {
            noteDao.delete(notes[0])
            return null
        }
    }

    private class DeleteAllNotesAsyncTask internal constructor(
        private val noteDao: GestionDao
    ) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            noteDao.deleteAllNotes()
            return null
        }

    }

}