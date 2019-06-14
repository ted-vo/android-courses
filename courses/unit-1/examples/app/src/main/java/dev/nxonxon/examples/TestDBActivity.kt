package dev.nxonxon.examples

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.CursorLoader
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dev.nxonxon.examples.database.StudentsContentProvider
import dev.nxonxon.examples.database.StudentsContentProvider.Companion.GRADE
import dev.nxonxon.examples.database.StudentsContentProvider.Companion.NAME
import kotlinx.android.synthetic.main.activity_test_db.*


class TestDBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_db)
    }

    fun onClickAddStudent(view: View) {
        // Add a new student record
        val values = ContentValues()
        values.put(NAME, edtName.text.toString())
        values.put(GRADE, edtGrade.text.toString())

        val uri = contentResolver.insert(Uri.parse(StudentsContentProvider.URL), values)
        Toast.makeText(baseContext, uri?.toString(), Toast.LENGTH_LONG).show()
    }

    fun onClickRetrieveStudents(view: View) {
        // Retrieve student records
        val students = Uri.parse(StudentsContentProvider.URL)
        val cursor = CursorLoader(this, students, null, null, null, "name")

//        if (cursor.isStarted) {
//            do {
//                Toast.makeText(this,
//                        cursor.getString(cursor.getColumnIndex(StudentsProvider._ID)) +
//                                ", " + cursor.getString(cursor.getColumnIndex(StudentsProvider.NAME)) +
//                                ", " + cursor.getString(cursor.getColumnIndex(StudentsProvider.GRADE)),
//                        Toast.LENGTH_SHORT).show()
//            } while (cursor.moveToNext())
//        }
    }
}