package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Dos_

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    /*se usa para acceder al dao, room se ocupa del código*/ /*se usa este metodo para acceder al ddbb*/
    abstract fun notesDao(): NoteDao
    abstract class PopulateddbbAsyncTask : AsyncTask<Void?, Void?, Void?> {
        private var noteDao: NoteDao

        constructor(
                noteDao: NoteDao
        ) {
            this.noteDao = noteDao
        }

        constructor(
                db: NoteDatabase?) {
            noteDao = db!!.notesDao()
            noteDao.insert(Note("Title 1", "Description 1", 1))
            noteDao.insert(Note("Title 2", "Description 2", 2))
            noteDao.insert(Note("Title 3", "Description 3", 3))

        }

        /**
         * @param voids
         */
       /* @Deprecated("")
        protected override fun doInBackground(vararg voids: Void): Void? {
            return null
        }*/
    }

    companion object {
        /*singleton*/
        private var instance: NoteDatabase? = null

        /*se crea la data base con otro singleton*/
        @Synchronized
        fun getInstance(context: Context): NoteDatabase? {
            /*se usa una sola instancia de la ddbb*/
            /*syn sig que una sola vez se use el metodo si es que instance es null*/
            if (instance == null) {
                /*no se llama Note database porque es abstract*/
                /*en su lugar se usa builder*/
                instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,
                        "note_database")
//                        .fallbackToDestructiveMigration() /*----permite crear algo en la ddb cuandro se crea por primera vez----*/
                        .addCallback(roomcallback)
                        .build()
            }
            return instance
        }

        var roomcallback: Callback = object : Callback() {
            /*se le llama cuando la ddb se crea*/
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
//                PopulateddbbAsyncTask(instance).execute()
            }

            /**
             * Called when the database has been opened.
             *
             * @param db The database.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }
    }
}