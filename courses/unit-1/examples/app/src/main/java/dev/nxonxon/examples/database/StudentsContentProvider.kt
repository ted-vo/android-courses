package dev.nxonxon.examples.database

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri


class StudentsContentProvider : ContentProvider() {

    private val contentUri = Uri.parse(URL)
    private val studentsMap: HashMap<String, String>? = null
    private var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(PROVIDER_NAME, "students", STUDENTS)
        uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID)
    }

    // Database specific constant declarations.
    private var db: SQLiteDatabase? = null

    override fun onCreate(): Boolean {
        context?.let {
            // Create a write able database which will trigger its creation if it doesn't already exist.
            db = DatabaseHelper(it, DATABASE_NAME, DATABASE_VERSION).writableDatabase
        }
        return db == null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // Add a new student record
        val rowId = db?.insert(STUDENTS_TABLE_NAME, "", values)

        // if record is added successfully
        if (rowId != null && rowId > 0) {
            val newURI = ContentUris.withAppendedId(contentUri, rowId)
            context?.contentResolver?.notifyChange(newURI, null)
            return uri
        }
        throw SQLException("Failed to add a record into $uri")
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val qb = SQLiteQueryBuilder().apply {
            tables = STUDENTS_TABLE_NAME
        }

        when (uriMatcher.match(uri)) {
            STUDENTS -> qb.setProjectionMap(studentsMap)
            STUDENT_ID -> qb.appendWhere("$ID = ${uri.pathSegments[1]}")
        }

        val sortOrderQuery =
            if (sortOrder == null || sortOrder === "") {
                // By default sort on student names
                NAME
            } else {
                sortOrder
            }

        val cursor = qb.query(
            db,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrderQuery
        )

        // register to watch a content URI for changes
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val count: Int
        when (uriMatcher.match(uri)) {
            STUDENTS -> {
                count = db?.update(
                    STUDENTS_TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
                ) ?: 0
            }
            STUDENT_ID -> {
                val id = uri.pathSegments[1]
                val selectionQuery =
                    if (selection?.isNotEmpty() == true) "AND $selection"
                    else ""
                count = db?.update(
                    STUDENTS_TABLE_NAME,
                    values,
                    "$ID = $id $selectionQuery",
                    selectionArgs
                ) ?: 0
            }
            else -> throw  IllegalArgumentException("Unknown URI $uri")
        }

        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val count: Int
        when (uriMatcher.match(uri)) {
            STUDENTS -> {
                count = db?.delete(
                    STUDENTS_TABLE_NAME,
                    selection,
                    selectionArgs
                ) ?: 0
            }
            STUDENT_ID -> {
                val id = uri.pathSegments[1]
                val selectionQuery =
                    if (selection?.isNotEmpty() == true) "AND $selection"
                    else ""
                count = db?.delete(
                    STUDENTS_TABLE_NAME,
                    "$ID = $id $selectionQuery",
                    selectionArgs
                ) ?: 0
            }
            else -> throw  IllegalArgumentException("Unknown URI $uri")
        }

        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            // Get all student records
            STUDENTS -> "vnd.android.cursor.dir/vnd.example.students"
            // Get a particular student
            STUDENT_ID -> "vnd.android.cursor.item/vnd.example.students"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    companion object {
        const val PROVIDER_NAME = "dev.nxonxon.Example.StudentsProvider"
        const val URL: String = "content://$PROVIDER_NAME/students"
        const val ID = "_id"
        const val NAME = "name"
        const val GRADE = "grade"

        const val STUDENTS = 1
        const val STUDENT_ID = 2

        const val DATABASE_NAME = "College"
        const val STUDENTS_TABLE_NAME = "students"
        const val DATABASE_VERSION = 1
    }
}