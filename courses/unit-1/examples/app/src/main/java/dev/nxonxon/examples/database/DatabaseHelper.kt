package dev.nxonxon.examples.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.nxonxon.examples.database.StudentsContentProvider.Companion.STUDENTS_TABLE_NAME

/**
 * Helper class that actually creates and manages
 * the provider's underlying data repository.
 */
class DatabaseHelper(context: Context, dbName: String, dbVersion: Int) :
    SQLiteOpenHelper(context, dbName, null, dbVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $STUDENTS_TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, grade TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $STUDENTS_TABLE_NAME")
        onCreate(db)
    }
}